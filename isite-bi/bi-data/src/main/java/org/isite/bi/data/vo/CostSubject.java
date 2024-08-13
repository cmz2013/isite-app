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
public class CostSubject {

    private Integer projectId;

    private Integer itemId;

    private Integer stageId;

    private String name;

    private String code;

    private String unit;

    private CostType costType;
}
