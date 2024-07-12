package org.isite.exam.core;

import org.isite.exam.data.vo.Question;
import org.isite.exam.data.vo.UserAnswer;

/**
 * @Description 考题模糊匹配接口
 * @Author <font color='blue'>zhangcm</font>
 */
public interface AnswerFuzzyMatcher<Q extends Question> {
    /**
     * 模糊匹配用户答案
     * @param question 题目
     * @param userAnswer 用户答案
     * @return 匹配系数
     */
    int match(Q question, UserAnswer userAnswer);

    /**
     * 获取题目系数
     * @param question 题目
     * @return 题目系数
     */
    int getCoefficient(Q question);
}
