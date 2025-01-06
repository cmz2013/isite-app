package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.cloud.data.op.Add;
import org.isite.commons.cloud.data.op.Update;

import javax.validation.constraints.NotNull;

/**
 * @Description 选择题的选项
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class OptionDto {
    /**
     * 值
     */
    @NotNull(groups = {Add.class, Update.class})
    private Integer value;
    /**
     * 标签（eg：A、B、C、D）
     */
    @NotNull(groups = {Add.class, Update.class})
    private String label;
    /**
     * 标题
     */
    @NotNull(groups = {Add.class, Update.class})
    private String title;
}
