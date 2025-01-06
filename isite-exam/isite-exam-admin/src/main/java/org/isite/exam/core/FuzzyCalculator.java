package org.isite.exam.core;

import org.isite.exam.data.enums.ScoreAlgorithm;
import org.isite.exam.data.vo.ExamModule;
import org.isite.exam.data.vo.Question;
import org.isite.exam.data.vo.UserAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.isite.commons.lang.Constants.ONE;
import static org.isite.commons.lang.Constants.ZERO;
import static org.isite.exam.data.enums.ScoreAlgorithm.FUZZY;

/**
 * @Description 模糊匹配正确答案，计算用户得分时使用评分规则设置的题目总分。
 * 考题得分 = 匹配系数 × 题目总分 ÷ 题目总数 ÷ 题目系数
 * 注意：计算总分的时候，不能因为小数丢失精度，即使用户答题全对，也不能得满分
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class FuzzyCalculator implements ScoreCalculator {

	private FuzzyMatcherFactory fuzzyMatcherFactory;

	@Override
	public int getUserScore(ExamModule examModule, Map<Long, UserAnswer> userAnswers) {
		int userScore = ZERO;
		int questionCount = examModule.getQuestions().size();
		int questionScore = examModule.getScore() / questionCount;
		int lastIndex = questionCount - ONE;
		int index = ZERO;

		FuzzyMatcher<Question> matcher = fuzzyMatcherFactory.get(examModule.getQuestionType());
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

	@Autowired
	public void setFuzzyMatcherFactory(FuzzyMatcherFactory fuzzyMatcherFactory) {
		this.fuzzyMatcherFactory = fuzzyMatcherFactory;
	}

	@Override
	public ScoreAlgorithm[] getIdentities() {
		return new ScoreAlgorithm[] {FUZZY};
	}
}
