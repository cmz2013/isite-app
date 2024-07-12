package org.isite.exam.converter;

import org.isite.exam.po.QuestionPo;
import org.isite.commons.lang.Key;
import org.isite.exam.data.dto.SingleChoiceDto;
import org.isite.exam.data.vo.SingleChoice;
import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;
import static org.isite.exam.data.constants.ExamConstants.QUESTION_JUDGMENT;
import static org.isite.exam.data.constants.ExamConstants.QUESTION_SINGLE_CHOICE;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Component
@Key(values = {QUESTION_SINGLE_CHOICE, QUESTION_JUDGMENT})
public class SingleChoiceConverter extends
        ObjectiveQuestionConverter<SingleChoice, SingleChoiceDto, Integer> {

    @Override
    protected Integer toRightAnswer(QuestionPo questionPo) {
        return parseInt(questionPo.getRightAnswer());
    }

    /**
     * 单选题正确答案转PO
     */
    @Override
    protected String toRightAnswer(SingleChoiceDto questionDto) {
        return questionDto.getRightAnswer().toString();
    }
}
