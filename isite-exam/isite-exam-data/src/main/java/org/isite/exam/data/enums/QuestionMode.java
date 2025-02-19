package org.isite.exam.data.enums;

import org.isite.commons.lang.Constants;
import org.isite.commons.lang.enums.Enumerable;
/**
 * @Description 选题方式
 * @Author <font color='blue'>zhangcm</font>
 */
public enum QuestionMode implements Enumerable<Integer> {
    /**
     * 手动选题
     */
    MANUALLY_SELECT(Constants.ZERO),
    /**
     * 随机选题
     */
    RANDOM_SELECT(Constants.ONE);

    private final Integer code;

    QuestionMode(Integer code) {
        this.code = code;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
