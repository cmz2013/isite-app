package org.isite.exam.converter;

import org.isite.commons.lang.Key;
import org.isite.exam.data.dto.ShortAnswerDto;
import org.isite.exam.data.vo.ShortAnswer;
import org.springframework.stereotype.Component;

import static org.isite.exam.data.constants.ExamConstants.QUESTION_SHORT_ANSWER;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Component
@Key(values = {QUESTION_SHORT_ANSWER})
public class ShortAnswerConverter extends SubjectiveQuestionConverter<ShortAnswer, ShortAnswerDto> {
}
