package org.isite.exam.core;

import org.isite.commons.lang.Key;
import org.isite.exam.data.vo.ExamModule;
import org.isite.exam.data.vo.Question;
import org.isite.exam.data.vo.UserAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.isite.commons.lang.data.Constants.ONE;
import static org.isite.commons.lang.data.Constants.ZERO;
import static org.isite.exam.data.constants.ExamConstants.TOTAL_SCORE_FUZZY;

/**
 * @Description 模糊匹配正确答案，使用评分规则设置的题目总分。
 * 选题规则选取的考题得分 = 匹配系数 × 选题规则题目总分 ÷ 选题规则选取的题数 ÷ 考题系数
 * 注意：计算总分的时候，不能因为小数丢失精度，即使用户答题全对，也不能得满分
 * @author <font color='blue'>zhangcm</font>
 */
@Component
@Key(values = TOTAL_SCORE_FUZZY)
public class TotalScoreFuzzyCalculator implements ScoreCalculator {

	private AnswerFuzzyMatcherFactory answerFuzzyMatcherFactory;

	@Override
	public int getUserScore(ExamModule examModule, Map<Long, UserAnswer> userAnswers) {
		int userScore = ZERO;
		int questionCount = examModule.getQuestions().size();
		int questionScore = examModule.getScore() / questionCount;
		int lastIndex = questionCount - ONE;
		int index = ZERO;

		AnswerFuzzyMatcher<Question> matcher = answerFuzzyMatcherFactory.get(examModule.getQuestionType());
		while (index < lastIndex) {
			Question question = examModule.getQuestions().get(index);
			userScore += matcher.match(question, userAnswers.get(question.getId())) *
					questionScore / matcher.getCoefficient(question);
			index++;
		}

		Question question = examModule.getQuestions().get(index);
		userScore += matcher.match(question, userAnswers.get(question.getId())) * (examModule.getScore() -
				questionScore * lastIndex) / matcher.getCoefficient(question);
		return userScore;
	}

	@Override
	public int getTotalScore(ExamModule examModule) {
		return examModule.getScore();
	}

	@Autowired
	public void setAnswerFuzzyMatcherFactory(AnswerFuzzyMatcherFactory answerFuzzyMatcherFactory) {
		this.answerFuzzyMatcherFactory = answerFuzzyMatcherFactory;
	}
}
