package org.isite.bi.cost;

import org.isite.bi.data.vo.CostRule;
import org.isite.bi.data.vo.CostSubject;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class CostRulePair {
    /**
     * 费用科目
     */
    private CostSubject subject;
    /**
     * 费用科目匹配到的规则
     */
    private CostRule rule;
    /**
     * 费用科目规则所在的树层级
     */
    private int level;

    public CostRulePair(Integer level, CostSubject subject, CostRule rule) {
        this.level = level;
        this.subject = subject;
        this.rule = rule;
    }
}
