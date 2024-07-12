package org.isite.exam.core;

import org.isite.exam.data.vo.ExamModule;
import org.isite.exam.data.vo.UserAnswer;

import java.util.Map;

/**
 * @Description 考试算分接口
 * @Author <font color='blue'>zhangcm</font>
 */
public interface ScoreCalculator {
	/**
	 * @Description 计算用户得分
	 * @param examModule 考卷组成元素
	 * @param userAnswers 用户答题记录
	 * @return 用户得分
	 */
	int getUserScore(ExamModule examModule, Map<Long, UserAnswer> userAnswers);

	/**
	 * @Description 计算总分
	 * @param examModule 考卷组成元素
	 * @return 总分
	 */
	int getTotalScore(ExamModule examModule);
}
