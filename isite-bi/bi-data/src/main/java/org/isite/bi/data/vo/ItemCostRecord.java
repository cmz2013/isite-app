package org.isite.bi.data.vo;

import lombok.Getter;
import lombok.Setter;
import org.isite.bi.data.enums.CostType;
import org.isite.commons.lang.data.Vo;

/**
 * @Description 费用记录
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class ItemCostRecord extends Vo<Long> {
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
