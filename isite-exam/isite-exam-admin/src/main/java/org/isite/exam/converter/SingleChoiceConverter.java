package org.isite.exam.converter;

import org.isite.exam.data.dto.SingleChoiceDto;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.SingleChoice;
import org.isite.exam.po.QuestionPo;
import org.springframework.stereotype.Component;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class SingleChoiceConverter extends
        ObjectiveQuestionConverter<SingleChoice, SingleChoiceDto, Integer> {

    @Override
    protected Integer toRightAnswer(QuestionPo questionPo) {
        return Integer.parseInt(questionPo.getRightAnswer());
    }

    /**
     * 单选题正确答案转PO
     */
    @Override
    protected String toRightAnswer(SingleChoiceDto questionDto) {
        return questionDto.getRightAnswer().toString();
    }

    @Override
    public QuestionType[] getIdentities() {
        return new QuestionType[] {QuestionType.SINGLE_CHOICE, QuestionType.JUDGMENT};
    }
}
