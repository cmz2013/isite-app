package org.isite.bi.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.bi.data.enums.CostType;
import org.isite.mybatis.data.Po;
import org.isite.mybatis.type.EnumTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @Description 费用记录
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "item_cost")
public class ItemCostPo extends Po<Long> {
    /**
     * 项目ID
     */
    private Integer projectId;
    /**
     * 单项ID
     */
    private Integer itemId;
    /**
     * 造价阶段ID
     */
    private Integer stageId;
    /**
     * 费用类型
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private CostType costType;
    /**
     * 金额
     */
    private BigDecimal amount;
}
