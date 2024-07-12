package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 选项
 *
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class Option {
    /**
     * 值
     */
    private Integer value;
    /**
     * 标签（eg：A、B、C、D）
     */
    private String label;
    /**
     * 标题
     */
    private String title;
}
