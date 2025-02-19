package org.isite.exam.core;

import org.apache.commons.collections4.CollectionUtils;
import org.isite.commons.cloud.converter.DataConverter;
import org.isite.exam.data.enums.QuestionMode;
import org.isite.exam.po.ExamQuestionPo;
import org.isite.exam.po.QuestionPo;
import org.isite.exam.service.ExamQuestionService;
import org.isite.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
/**
 * @Description 手动组卷接口
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class ManualAccessor extends ExamAccessor {
    private QuestionService questionService;
    private ExamQuestionService examQuestionService;

    @Override
    protected List<QuestionPo> findQuestions(int examPaperId) {
        ExamQuestionPo query = new ExamQuestionPo();
        query.setExamPaperId(examPaperId);
        List<Integer> questionIds = DataConverter.convert(examQuestionService.findList(query), ExamQuestionPo::getQuestionId);
        return CollectionUtils.isEmpty(questionIds) ? Collections.emptyList() :
                questionService.findIn(QuestionPo::getId, questionIds);
    }

    @Autowired
    public void setExamQuestionService(ExamQuestionService examQuestionService) {
        this.examQuestionService = examQuestionService;
    }

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public QuestionMode[] getIdentities() {
        return new QuestionMode[] {QuestionMode.MANUALLY_SELECT};
    }
}
