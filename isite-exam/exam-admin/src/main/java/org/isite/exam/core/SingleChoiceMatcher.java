package org.isite.exam.core;

import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.SingleChoice;
import org.isite.exam.data.vo.UserAnswer;
import org.springframework.stereotype.Component;

import static java.lang.Boolean.FALSE;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.isite.exam.data.enums.QuestionType.JUDGMENT;
import static org.isite.exam.data.enums.QuestionType.SINGLE_CHOICE;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class SingleChoiceMatcher implements ExactMatcher<SingleChoice> {

    @Override
    public boolean match(SingleChoice question, UserAnswer userAnswer) {
        return null == userAnswer || isBlank(userAnswer.getAnswer()) ? FALSE :
                question.getRightAnswer().equals(Integer.parseInt(userAnswer.getAnswer()));
    }

    @Override
    public QuestionType[] getIdentities() {
        return new QuestionType[] { SINGLE_CHOICE, JUDGMENT };
    }
}
