package org.isite.exam.core;

import org.isite.commons.lang.Key;
import org.isite.exam.data.vo.ExamModule;
import org.isite.exam.data.vo.Question;
import org.isite.exam.data.vo.UserAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.isite.commons.lang.data.Constants.ZERO;
import static org.isite.exam.data.constants.ExamConstants.SINGLE_SCORE_FUZZY;

/**
 * 模糊匹配正确答案，使用评分规则设置的每道题分数
 * 选题规则选取的考题得分 = 匹配系数 × 选题规则题目分数 ÷ 考题系数
 * 注意：计算总分的时候，不能预先计算出题目分数，因为如果是小数可能丢失精度，即使用户答题全对，也不能得满分
 *
 * @author <font color='blue'>zhangcm</font>
 */
@Component
@Key(values = SINGLE_SCORE_FUZZY)
public class SingleScoreFuzzyCalculator implements ScoreCalculator {

    private AnswerFuzzyMatcherFactory answerFuzzyMatcherFactory;

    @Override
    public int getUserScore(ExamModule examModule, Map<Long, UserAnswer> userAnswers) {
        int userScore = ZERO;
        AnswerFuzzyMatcher<Question> matcher = answerFuzzyMatcherFactory.get(examModule.getQuestionType());
        for (Question question : examModule.getQuestions()) {
            UserAnswer userAnswer = userAnswers.get(question.getId());
            if (null == userAnswer) {
                continue;
            }
            userAnswer.setScore(matcher.match(question, userAnswer) *
                    examModule.getScore() / matcher.getCoefficient(question));
            userScore += userAnswer.getScore();
        }
        return userScore;
    }

    @Override
    public int getTotalScore(ExamModule examModule) {
        return examModule.getScore() * examModule.getQuestions().size();
    }

    @Autowired
    public void setAnswerFuzzyMatcherFactory(AnswerFuzzyMatcherFactory answerFuzzyMatcherFactory) {
        this.answerFuzzyMatcherFactory = answerFuzzyMatcherFactory;
    }
}
