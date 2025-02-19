package org.isite.exam.core;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.isite.commons.lang.json.Jackson;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.MultipleChoice;
import org.isite.exam.data.vo.UserAnswer;
import org.springframework.stereotype.Component;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class MultipleChoiceExactMatcher implements ExactMatcher<MultipleChoice> {

    @Override
    public boolean match(MultipleChoice question, UserAnswer userAnswer) {
        return null == userAnswer || StringUtils.isBlank(userAnswer.getAnswer()) ? Boolean.FALSE : CollectionUtils.isEqualCollection(
                question.getRightAnswer(), Jackson.parseArray(userAnswer.getAnswer(), Integer.class));
    }

    @Override
    public QuestionType[] getIdentities() {
        return new QuestionType[] {QuestionType.MULTIPLE_CHOICE};
    }
}
