package org.isite.exam.core;

import com.github.pagehelper.Page;
import org.apache.commons.collections4.CollectionUtils;
import org.isite.commons.lang.Constants;
import org.isite.commons.lang.schedule.RandomScheduler;
import org.isite.exam.data.enums.QuestionMode;
import org.isite.exam.po.QuestionPo;
import org.isite.exam.po.QuestionRulePo;
import org.isite.exam.service.QuestionRuleService;
import org.isite.exam.service.QuestionService;
import org.isite.jpa.data.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * @Description 随机组卷接口
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class RandomAccessor extends ExamAccessor {
    private QuestionService questionService;
    private QuestionRuleService questionRuleService;

    @Override
    protected List<QuestionPo> findQuestions(int examPaperId) {
        int offset = Constants.ZERO;
        List<QuestionRulePo> questionRules = questionRuleService.findByPaperId(examPaperId);
        Map<Integer, Integer> questionTotals = countQuestionTotals(questionRules);
        List<QuestionPo> questionPos = new ArrayList<>();
        for (QuestionRulePo questionRule : sortQuestionRules(questionRules, questionTotals)) {
            //如果选取的题目数量小于当前选题规则设置的题数时，就把未选的题数累加到下一个选题规则的题数上，继续选题
            int number = offset + questionRule.getNumber();
            questionRule.setNumber(number + questionRule.getNumber() / Constants.THREE);
            try (Page<QuestionPo> page = findPage(questionRule, questionTotals.get(questionRule.getId()))) {
                if (CollectionUtils.isNotEmpty(page.getResult())) {
                    offset = number - page.size();
                    while (offset < Constants.ZERO) {
                        page.remove(RandomScheduler.nextInt(page.getResult().size()));
                        offset++;
                    }
                    questionPos.addAll(page.getResult());
                } else {
                    offset = questionRule.getNumber();
                }
            }
        }
        Collections.shuffle(questionPos);
        return questionPos;
    }

    /**
     * 选题个数大于题目总数的规则排在前边
     */
    private List<QuestionRulePo> sortQuestionRules(
            List<QuestionRulePo> questionRules, Map<Integer, Integer> questionTotals) {
        //comparingInt将QuestionRulePo转换为: 题目总数-选题个数，选题规则基于这个值进行排序
        return questionRules.stream().sorted(Comparator.comparingInt(o ->
                (questionTotals.get(o.getId()) - o.getNumber()))).collect(Collectors.toList());
    }

    /**
     * 查询选题规则题目总数
     */
    private Map<Integer, Integer> countQuestionTotals(List<QuestionRulePo> questionRulePos) {
        Map<Integer, Integer> questionTotals = new HashMap<>(questionRulePos.size());
        questionRulePos.forEach(item -> questionTotals.put(item.getId(),
                questionService.count(item.getPoolId(), item.getQuestionType())));
        return questionTotals;
    }


    /**
     * 根据选题规则随机分页查询题目
     */
    private Page<QuestionPo> findPage(QuestionRulePo rulePo, int total) {
        QuestionPo questionPo = new QuestionPo();
        questionPo.setPoolId(rulePo.getPoolId());
        questionPo.setQuestionType(rulePo.getQuestionType());
        int bound = total - rulePo.getNumber();
        PageQuery<QuestionPo> pageQuery = new PageQuery<>() {
            @Override
            public int getOffset() {
                return bound > Constants.ZERO ? RandomScheduler.nextInt(bound + Constants.ONE) : Constants.ZERO;
            }
        };
        pageQuery.setPageSize(rulePo.getNumber());
        pageQuery.setPo(questionPo);
        return questionService.findPage(pageQuery);
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Autowired
    public void setQuestionRuleService(QuestionRuleService questionRuleService) {
        this.questionRuleService = questionRuleService;
    }

    @Override
    public QuestionMode[] getIdentities() {
        return new QuestionMode[] {QuestionMode.RANDOM_SELECT};
    }
}
