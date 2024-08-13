package org.isite.bi.cost;

import lombok.Getter;
import lombok.Setter;
import org.isite.bi.data.vo.CostRule;
import org.isite.bi.data.vo.CostSubject;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class CostIndexPair {
    /**
     * 费用科目规则树层级
     */
    private int level;
    /**
     * 费用科目
     */
    private CostSubject costSubject;
    /**
     * 匹配到的费用指标
     */
    private CostRule costRule;

    public CostIndexPair(Integer level, CostSubject costSubject, CostRule costRule) {
        this.level = level;
        this.costSubject = costSubject;
        this.costRule = costRule;
    }
}
