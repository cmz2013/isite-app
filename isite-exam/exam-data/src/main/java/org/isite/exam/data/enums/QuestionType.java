package org.isite.exam.data.enums;

import org.isite.commons.lang.enums.Enumerable;

/**
 * @Description 题目类型
 * @Author <font color='blue'>zhangcm</font>
 */
public enum QuestionType implements Enumerable<Integer> {
    /**
     * 单选题
     */
    SINGLE_CHOICE(0),
    /**
     * 多选题
     */
    MULTIPLE_CHOICE(1),
    /**
     * 判断题（属于单选题）
     */
    JUDGMENT(2),
    /**
     * 简答题
     */
    SHORT_ANSWER(3);

    private final Integer code;

    QuestionType(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
