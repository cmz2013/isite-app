package org.isite.bi.data.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description 项目费用科目
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class CostSubject {

    private String name;

    private String code;

    private String unit;

    private String type;

    private Integer projectId;

    private Integer itemId;

    private Integer stageId;
}
