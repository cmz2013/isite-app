package org.isite.exam.converter;

import org.isite.exam.data.dto.ShortAnswerDto;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.ShortAnswer;
import org.springframework.stereotype.Component;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class ShortAnswerConverter extends SubjectiveQuestionConverter<ShortAnswer, ShortAnswerDto> {
    @Override
    public QuestionType[] getIdentities() {
        return new QuestionType[] {QuestionType.SHORT_ANSWER};
    }
}
