package org.isite.exam.data.enums;

import org.isite.commons.lang.enums.Enumerable;

/**
 * 问题难度
 *
 * @author <font color='blue'>zhangcm</font>
 */
public enum DifficultyLevel implements Enumerable<Integer> {
    /**
     * 易
     */
    EASY(0),
    /**
     * 中
     */
    MIDDLE(1),
    /**
     * 难
     */
    HARD(2);

    private final Integer code;

    DifficultyLevel(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
