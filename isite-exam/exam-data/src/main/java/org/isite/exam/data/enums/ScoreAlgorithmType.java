package org.isite.exam.data.enums;

import org.isite.commons.lang.enums.Enumerable;

/**
 * @Description 考试分数算法类型
 * @Author <font color='blue'>zhangcm</font>
 */
public enum ScoreAlgorithmType implements Enumerable<Integer> {
	/**
	 * 人工判断考试得分，使用评分规则设置的每道题分数
	 */
	SINGLE_SCORE_MANUAL(1),
	/**
	 * 人工判断考试得分，使用评分规则设置的题目总分
	 */
	TOTAL_SCORE_MANUAL(2),
	/**
	 * 精确匹配正确答案，使用评分规则设置的每道题分数
	 */
	SINGLE_SCORE_EXACT(3),
	/**
	 * 精确匹配正确答案，使用评分规则设置的题目总分
	 * 选题规则选取的考题得分 = 答对题数 × 选题规则题目总分 ÷ 选题规则选取的题数
	 *
	 * 注意：
	 * 1)计算总分的时候，不能预先计算出题目分数，因为如果是小数可能丢失精度，即使用户答题全对，也不能得满分
	 * 2)如果是多选题型，少选得0分
	 */
	TOTAL_SCORE_EXACT(4),
	/**
	 * 模糊匹配正确答案，使用评分规则设置的每道题分数
	 * 选题规则选取的考题得分 = 匹配系数 × 选题规则题目分数 ÷ 考题系数
	 * 注意：计算总分的时候，不能预先计算出题目分数，因为如果是小数可能丢失精度，即使用户答题全对，也不能得满分
	 */
	SINGLE_SCORE_FUZZY(5),
	/**
	 * 模糊匹配正确答案，使用评分规则设置的题目总分
	 * 选题规则选取的考题得分 = 匹配系数 × 选题规则题目总分 ÷ 选题规则选取的题数 ÷ 考题系数
	 * 注意：计算总分的时候，不能预先计算出题目分数，因为如果是小数可能丢失精度，即使用户答题全对，也不能得满分
	 */
	TOTAL_SCORE_FUZZY(6);

	private final Integer code;

	ScoreAlgorithmType(Integer code) {
		this.code = code;
	}

	@Override
	public Integer getCode() {
		return this.code;
	}
}
