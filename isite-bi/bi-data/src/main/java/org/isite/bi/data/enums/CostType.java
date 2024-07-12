package org.isite.bi.data.enums;

import lombok.Getter;
import org.isite.commons.lang.enums.Enumerable;

/**
 * @Description 费用类型
 * @Author <font color='blue'>zhangcm</font>
 */
public enum CostType implements Enumerable<String> {
    /**
     * 单项费用
     */
    ITEM("item", 0);

    /**
     * 算法标识
     */
    private final String code;
    /**
     * 控制多个算法之间的计算顺序
     */
    @Getter
    private final int order;

    CostType(String code, int order) {
        this.code = code;
        this.order = order;
    }

    @Override
    public String getCode() {
        return code;
    }

}
