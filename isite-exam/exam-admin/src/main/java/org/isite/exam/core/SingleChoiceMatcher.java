package org.isite.exam.core;

import org.isite.commons.lang.Key;
import org.isite.exam.data.vo.SingleChoice;
import org.isite.exam.data.vo.UserAnswer;
import org.springframework.stereotype.Component;

import static java.lang.Boolean.FALSE;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.isite.exam.data.constants.ExamConstants.QUESTION_JUDGMENT;
import static org.isite.exam.data.constants.ExamConstants.QUESTION_SINGLE_CHOICE;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
@Key(values = {QUESTION_SINGLE_CHOICE, QUESTION_JUDGMENT})
public class SingleChoiceMatcher implements ExactMatcher<SingleChoice> {

    @Override
    public boolean match(SingleChoice question, UserAnswer userAnswer) {
        return null == userAnswer || isBlank(userAnswer.getAnswer()) ? FALSE :
                question.getRightAnswer().equals(Integer.parseInt(userAnswer.getAnswer()));
    }
}
