package org.isite.exam.core;

import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.MultipleChoice;
import org.isite.exam.data.vo.UserAnswer;
import org.springframework.stereotype.Component;

import static java.lang.Boolean.FALSE;
import static org.apache.commons.collections4.CollectionUtils.isEqualCollection;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.isite.commons.lang.json.Jackson.parseArray;
import static org.isite.exam.data.enums.QuestionType.MULTIPLE_CHOICE;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class MultipleChoiceExactMatcher implements ExactMatcher<MultipleChoice> {

    @Override
    public boolean match(MultipleChoice question, UserAnswer userAnswer) {
        return null == userAnswer || isBlank(userAnswer.getAnswer()) ? FALSE : isEqualCollection(
                question.getRightAnswer(), parseArray(userAnswer.getAnswer(), Integer.class));
    }

    @Override
    public QuestionType[] getIdentities() {
        return new QuestionType[] {MULTIPLE_CHOICE};
    }
}
