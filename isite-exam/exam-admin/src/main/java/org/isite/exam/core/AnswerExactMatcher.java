package org.isite.exam.core;

import org.isite.exam.data.vo.Question;
import org.isite.exam.data.vo.UserAnswer;

/**
 * @Description 考题答案精确匹配接口
 * @Author <font color='blue'>zhangcm</font>
 */
public interface AnswerExactMatcher<Q extends Question> {
    /**
     * 精确匹配用户答案
     * @param question 题目
     * @param userAnswer 用户答案
     * @return true 正确，false 错误
     */
    boolean match(Q question, UserAnswer userAnswer);
}
