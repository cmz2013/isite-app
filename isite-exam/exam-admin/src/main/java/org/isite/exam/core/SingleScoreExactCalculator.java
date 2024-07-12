package org.isite.exam.core;

import org.isite.commons.lang.Key;
import org.isite.exam.data.vo.ExamModule;
import org.isite.exam.data.vo.Question;
import org.isite.exam.data.vo.UserAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.isite.commons.lang.data.Constants.ZERO;
import static org.isite.exam.data.constants.ExamConstants.SINGLE_SCORE_EXACT;

/**
 * @Description 精确匹配正确答案，使用评分规则设置的每道题分数计算用户得分
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
@Key(values = SINGLE_SCORE_EXACT)
public class SingleScoreExactCalculator implements ScoreCalculator {

    private AnswerExactMatcherFactory answerExactMatcherFactory;

    @Override
    public int getUserScore(ExamModule examModule, Map<Long, UserAnswer> userAnswers) {
        int userScore = ZERO;
        AnswerExactMatcher<Question> matcher = answerExactMatcherFactory.get(examModule.getQuestionType());
        for (Question question : examModule.getQuestions()) {
            UserAnswer userAnswer = userAnswers.get(question.getId());
            if (null == userAnswer) {
                continue;
            }
            if (matcher.match(question, userAnswer)) {
                userScore += examModule.getScore();
                userAnswer.setScore(examModule.getScore());
            }
        }
        return userScore;
    }

    @Override
    public int getTotalScore(ExamModule examModule) {
        return examModule.getScore() * examModule.getQuestions().size();
    }

    @Autowired
    public void setAnswerExactMatcherFactory(AnswerExactMatcherFactory answerExactMatcherFactory) {
        this.answerExactMatcherFactory = answerExactMatcherFactory;
    }
}
