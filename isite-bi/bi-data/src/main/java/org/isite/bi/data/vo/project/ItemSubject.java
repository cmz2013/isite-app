package org.isite.bi.data.vo.project;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Description 单项费用科目
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class ItemSubject extends CostSubject {
    /**
     * 金额
     */
    private BigDecimal amount;
}
