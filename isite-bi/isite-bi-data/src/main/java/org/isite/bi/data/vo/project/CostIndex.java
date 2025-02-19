package org.isite.bi.data.vo.project;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description 费用指标
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class CostIndex {
    /**
     * 费用科目规则树层级
     */
    private int level;
    /**
     * 费用科目
     */
    private CostSubject costSubject;
    /**
     * 匹配到的费用指标规则
     */
    private CostRule costRule;

    public CostIndex(Integer level, CostSubject costSubject, CostRule costRule) {
        this.level = level;
        this.costSubject = costSubject;
        this.costRule = costRule;
    }
}
