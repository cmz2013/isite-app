package org.isite.exam.data.enums;

import org.isite.commons.lang.enums.Enumerable;

import static org.isite.commons.lang.data.Constants.ONE;
import static org.isite.commons.lang.data.Constants.ZERO;

/**
 * @Description 选题方式
 * @Author <font color='blue'>zhangcm</font>
 */
public enum QuestionMode implements Enumerable<Integer> {
    /**
     * 手动组卷
     */
    MANUALLY(ZERO),
    /**
     * 随机组卷
     */
    RANDOM(ONE);

    private final Integer code;

    QuestionMode(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
