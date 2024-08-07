package org.isite.bi.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;

import javax.persistence.Table;

/**
 * @Description 项目费用参数，用于配置需要计算的项目费用
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "project_cost")
public class ProjectCostPo extends Po<Integer> {
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
