package org.isite.exam.core;

import org.apache.commons.lang3.StringUtils;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.SingleChoice;
import org.isite.exam.data.vo.UserAnswer;
import org.springframework.stereotype.Component;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class SingleChoiceMatcher implements ExactMatcher<SingleChoice> {

    @Override
    public boolean match(SingleChoice question, UserAnswer userAnswer) {
        return null == userAnswer || StringUtils.isBlank(userAnswer.getAnswer()) ? Boolean.FALSE :
                question.getRightAnswer().equals(Integer.parseInt(userAnswer.getAnswer()));
    }

    @Override
    public QuestionType[] getIdentities() {
        return new QuestionType[] {QuestionType.SINGLE_CHOICE, QuestionType.JUDGMENT};
    }
}
