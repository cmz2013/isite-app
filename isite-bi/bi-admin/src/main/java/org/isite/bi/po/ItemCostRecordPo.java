package org.isite.bi.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.bi.data.enums.CostType;
import org.isite.mybatis.data.Po;

import javax.persistence.Table;

/**
 * @Description 费用记录
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "item_cost_record")
public class ItemCostRecordPo extends Po<Long> {
    /**
     * 项目ID
     */
    private Integer projectId;
    /**
     * 费用类型
     */
    private CostType costType;
    /**
     * 单项ID
     */
    private Integer itemId;
    /**
     * 造价阶段ID
     */
    private Integer stageId;
}
