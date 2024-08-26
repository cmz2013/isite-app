package org.isite.bi.data.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description 项目费用参数
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class CostElement {
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

}
