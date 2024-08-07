package org.isite.exam.data.enums;

import org.isite.commons.lang.enums.Enumerable;

/**
 * @Description 考试分数算法类型
 * @Author <font color='blue'>zhangcm</font>
 */
public enum ScoreAlgorithm implements Enumerable<Integer> {
	/**
	 * 人工判断考试得分
	 */
	MANUAL(0),
	/**
	 * @Description 精确匹配正确答案，使用评分规则设置的题目总分
	 * 考题得分 = 答对题数 × 题目总分 ÷ 题目总数
	 * 1)计算总分的时候，不能预先计算出题目分数，因为如果是小数可能丢失精度，即使用户答题全对，也不能得满分
	 * 2)如果是多选题型，少选得0分
	 */
	EXACT(1),
	/**
	 * @Description 模糊匹配正确答案，使用评分规则设置的题目总分
	 * 考题得分 = 匹配系数 × 题目总分 ÷ 题目总数 ÷ 题目系数
	 * 注意：计算总分的时候，不能预先计算出题目分数，因为如果是小数可能丢失精度，即使用户答题全对，也不能得满分
	 */
	FUZZY(2);

	private final Integer code;

	ScoreAlgorithm(Integer code) {
		this.code = code;
	}

	@Override
	public Integer getCode() {
		return this.code;
	}
}
