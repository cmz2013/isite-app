package org.isite.bi.data.vo;

import lombok.Getter;
import lombok.Setter;
import org.isite.bi.data.enums.CostType;

/**
 * @Description 项目费用科目
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class CostSubject extends CostElement {
    /**
     * 费用类型
     */
    private CostType costType;
    /**
     * 科目名称
     */
    private String name;
    /**
     * 科目代码
     */
    private String code;
    /**
     * 费用单位
     */
    private String unit;
}
