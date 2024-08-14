package org.isite.exam.core;

import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.MultipleChoice;
import org.isite.exam.data.vo.UserAnswer;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.isite.commons.lang.data.Constants.ZERO;
import static org.isite.commons.lang.json.Jackson.parseArray;
import static org.isite.exam.data.enums.QuestionType.MULTIPLE_CHOICE;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class MultipleChoiceFuzzyMatcher implements FuzzyMatcher<MultipleChoice> {

    /**
     * 匹配用户多选题选项
     * @param question 多选题
     * @param userAnswer 用户答案
     * @return 选对选项数
     */
    @Override
    public int match(MultipleChoice question, UserAnswer userAnswer) {
        return null == userAnswer || isBlank(userAnswer.getAnswer()) ? ZERO : match(
                question.getRightAnswer(), parseArray(userAnswer.getAnswer(), Integer.class));
    }

    /**
     * 返回正确选项个数
     */
    @Override
    public int getCoefficient(MultipleChoice question) {
        return question.getRightAnswer().size();
    }

    /**
     * 错选不得分，少选得分
     * @return 选对选项数
     */
    private int match(Collection<Integer> rightAnswers, Collection<Integer> userAnswers) {
        int rightCount = ZERO;
        for (int userAnswer : userAnswers) {
            if (rightAnswers.contains(userAnswer)) {
                rightCount++;
            } else {
                return ZERO;
            }
        }
        return rightCount;
    }

    @Override
    public QuestionType[] getIdentities() {
        return new QuestionType[] { MULTIPLE_CHOICE };
    }
}
