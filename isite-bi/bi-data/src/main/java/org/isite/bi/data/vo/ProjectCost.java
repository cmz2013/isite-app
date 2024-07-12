package org.isite.bi.data.vo;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.lang.data.Vo;

/**
 * @Description 项目费用参数
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class ProjectCost extends Vo<Integer> {
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
