package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.cloud.data.dto.Dto;
import org.isite.commons.cloud.data.op.Add;
import org.isite.commons.cloud.data.op.Update;
import org.isite.misc.data.enums.ObjectType;

import javax.validation.constraints.NotNull;

/**
 * @Description 考试场景。一个试卷可用于不同的考试场景。
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class ExamSceneDto extends Dto<Integer> {
    /**
     * 考试名称
     */
    private String title;
    /**
     * 对象类型
     */
    private ObjectType objectType;
    /**
     * 对象值
     */
    private String objectValue;
    /**
     * 试卷ID。考试场景关联试卷
     */
    @NotNull(groups = {Add.class, Update.class})
    private Integer paperId;
    /**
     * 是否支持需考
     */
    private Boolean continues;
}
