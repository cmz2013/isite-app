package org.isite.exam.core;

import org.isite.exam.data.enums.ScoreAlgorithm;
import org.isite.exam.data.vo.ExamModule;
import org.isite.exam.data.vo.Question;
import org.isite.exam.data.vo.UserAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.isite.commons.lang.data.Constants.ZERO;
import static org.isite.exam.data.enums.ScoreAlgorithm.EXACT;

/**
 * @Description 精确匹配正确答案，计算用户得分时使用评分规则设置的题目总分。
 * 考题得分 = 答对题数 × 题目总分 ÷ 题目总数
 * 1)计算总分的时候，不能预先计算出题目分数，因为如果是小数可能丢失精度，即使用户答题全对，也不能得满分
 * 2)如果是多选题型，少选得0分
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class ExactCalculator implements ScoreCalculator {

	private ExactMatcherFactory exactMatcherFactory;

	@Override
	public int getUserScore(ExamModule examModule, Map<Long, UserAnswer> userAnswers) {
		int rightCount = ZERO;
		ExactMatcher<Question> matcher = exactMatcherFactory.get(examModule.getQuestionType());
		for (Question question : examModule.getQuestions()) {
			if (matcher.match(question, userAnswers.get(question.getId()))) {
				rightCount++;
			}
		}
		return rightCount * examModule.getScore() / examModule.getQuestions().size();
	}

	@Autowired
	public void setExactMatcherFactory(ExactMatcherFactory exactMatcherFactory) {
		this.exactMatcherFactory = exactMatcherFactory;
	}

	@Override
	public ScoreAlgorithm[] getIdentities() {
		return new ScoreAlgorithm[] { EXACT };
	}
}
