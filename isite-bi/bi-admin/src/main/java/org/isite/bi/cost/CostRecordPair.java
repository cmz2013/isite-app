package org.isite.bi.cost;

import lombok.Getter;
import lombok.Setter;
import org.isite.bi.data.vo.CostRule;
import org.isite.mybatis.data.Po;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class CostRecordPair<C extends Po<?>> {
    /**
     * 费用科目规则
     */
    private CostRule costRule;
    /**
     * 汇总的费用数据
     */
    private C costRecord;

    public CostRecordPair(CostRule costRule, C costRecord) {
        this.costRule = costRule;
        this.costRecord = costRecord;
    }
}
