package org.isite.bi.data.vo.project;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class CostSummary<C> {
    /**
     * 费用科目规则
     */
    private CostRule costRule;
    /**
     * 汇总的费用数据
     */
    private C costRecord;

    public CostSummary(CostRule costRule, C costRecord) {
        this.costRule = costRule;
        this.costRecord = costRecord;
    }
}
