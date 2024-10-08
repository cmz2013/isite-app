package org.isite.exam.core;

import org.isite.commons.cloud.factory.Strategy;
import org.isite.exam.converter.QuestionConverterFactory;
import org.isite.exam.data.enums.QuestionMode;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.ExamModule;
import org.isite.exam.data.vo.ScoreRule;
import org.isite.exam.po.QuestionPo;
import org.isite.exam.po.ScoreRulePo;
import org.isite.exam.service.ScoreRuleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.isite.commons.cloud.converter.DataConverter.convert;
import static org.isite.commons.cloud.converter.MapConverter.groupBy;
import static org.isite.commons.cloud.converter.MapConverter.toMap;

/**
 * @Description 组卷接口
 * @Author <font color='blue'>zhangcm</font>
 */
public abstract class ExamAccessor implements Strategy<QuestionMode> {

    private ScoreRuleService scoreRuleService;
    private QuestionConverterFactory questionConverterFactory;

    /**
     * @Description 获取试卷组成模块
     * @param paperId 试卷ID
     */
    public List<ExamModule> getExamModules(int paperId) {
        Map<QuestionType, List<QuestionPo>> questions =
                groupBy(QuestionPo::getQuestionType, findQuestions(paperId));
        Map<QuestionType, ScoreRulePo> scoreRules =
                toMap(ScoreRulePo::getQuestionType, scoreRuleService.findByPaperId(paperId));

        List<ExamModule> examModules = new ArrayList<>();
        questions.forEach((questionType, questionPos) -> examModules.add(new ExamModule(
                convert(scoreRules.get(questionType), ScoreRule::new),
                questionConverterFactory.get(questionType).toQuestions(questionPos))));
        return examModules;

    }

    /**
     * 查询试卷题目
     * @param paperId 试卷ID
     * @return 题目列表
     */
    protected abstract List<QuestionPo> findQuestions(int paperId);

    @Autowired
    public void setQuestionConverterFactory(QuestionConverterFactory questionConverterFactory) {
        this.questionConverterFactory = questionConverterFactory;
    }

    @Autowired
    public void setScoreRuleService(ScoreRuleService scoreRuleService) {
        this.scoreRuleService = scoreRuleService;
    }
}
