package org.isite.exam.core;

import com.github.pagehelper.Page;
import org.isite.commons.lang.Key;
import org.isite.exam.po.QuestionPo;
import org.isite.exam.po.QuestionRulePo;
import org.isite.exam.service.QuestionRuleService;
import org.isite.exam.service.QuestionService;
import org.isite.jpa.data.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.shuffle;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.isite.commons.lang.data.Constants.ONE;
import static org.isite.commons.lang.data.Constants.TWO;
import static org.isite.commons.lang.data.Constants.ZERO;
import static org.isite.commons.lang.schedule.RandomScheduler.nextInt;
import static org.isite.exam.data.constants.ExamConstants.EXAM_MODE_RANDOM;

/**
 * @Description 随机组卷接口
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
@Key(values = EXAM_MODE_RANDOM)
public class RandomAccessor extends ExamAccessor {
    private QuestionRuleService questionRuleService;
    private QuestionService questionService;

    @Override
    protected List<QuestionPo> findQuestions(int paperId) {
        int offset = ZERO;
        List<QuestionRulePo> questionRules = questionRuleService.findByPaperId(paperId);
        Map<Integer, Integer> questionTotals = countQuestionTotals(questionRules);
        List<QuestionPo> questionPos = new ArrayList<>();
        for (QuestionRulePo questionRule : sortQuestionRules(questionRules, questionTotals)) {
            //如果选取的题目数量小于当前选题规则设置的题数时，就把未选的题数累加到下一个选题规则的题数上，继续选题
            int number = offset + questionRule.getNumber();
            questionRule.setNumber(number + questionRule.getNumber() / TWO);
            try (Page<QuestionPo> page = findPage(questionRule, questionTotals.get(questionRule.getId()))) {
                if (isNotEmpty(page.getResult())) {
                    offset = number - page.size();
                    while (offset < ZERO) {
                        page.remove(nextInt(page.getResult().size()));
                        offset++;
                    }
                    questionPos.addAll(page.getResult());
                } else {
                    offset = questionRule.getNumber();
                }
            }
        }
        shuffle(questionPos);
        return questionPos;
    }

    /**
     * 选题个数大于题目总数的规则排在前边
     */
    private List<QuestionRulePo> sortQuestionRules(
            List<QuestionRulePo> questionRules, Map<Integer, Integer> questionTotals) {
        //comparingInt将QuestionRulePo转换为: 题目总数-选题个数，选题规则基于这个值进行排序
        return questionRules.stream().sorted(comparingInt(o ->
                (questionTotals.get(o.getId()) - o.getNumber()))).collect(toList());
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
        PageQuery<QuestionPo> pageQuery = new PageQuery<>();
        int offset = total - rulePo.getNumber();
        pageQuery.setOffset(offset > ZERO ? nextInt(offset + ONE) : ZERO);
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
}
