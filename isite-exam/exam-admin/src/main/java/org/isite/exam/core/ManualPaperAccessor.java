package org.isite.exam.core;

import org.isite.exam.service.ExamQuestionService;
import org.isite.exam.service.QuestionService;
import org.isite.commons.lang.Key;
import org.isite.exam.po.ExamQuestionPo;
import org.isite.exam.po.QuestionPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.isite.commons.cloud.data.Converter.convert;
import static org.isite.exam.data.constants.ExamConstants.EXAM_MODE_MANUALLY;

/**
 * @Description 手动组卷接口
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
@Key(values = EXAM_MODE_MANUALLY)
public class ManualPaperAccessor extends ExamPaperAccessor {

    private QuestionService questionService;
    private ExamQuestionService examQuestionService;

    @Override
    protected List<QuestionPo> findQuestions(int paperId) {
        ExamQuestionPo query = new ExamQuestionPo();
        query.setPaperId(paperId);
        List<Integer> questionIds = convert(examQuestionService.findList(query), ExamQuestionPo::getQuestionId);
        return isEmpty(questionIds) ? emptyList() :
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
}
