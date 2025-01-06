package org.isite.bi.data.vo.project;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.cloud.data.vo.Tree;

/**
 * @Description 费用科目规则树
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class CostRule extends Tree<CostRule, Integer> {
    /**
     * 指标类型
     */
    private String indexType;
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 用于过滤数据的java script
     */
    private String expressions;
}
