package org.isite.bi.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.bi.data.enums.CostType;
import org.isite.mybatis.data.TreePo;

import javax.persistence.Table;

/**
 * @Description 费用科目规则树PO
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "cost_rule")
public class CostRulePo extends TreePo<Integer> {
    /**
     * 费用类型
     */
    private CostType costType;
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 用于过滤数据的java script
     */
    private String expressions;
}
