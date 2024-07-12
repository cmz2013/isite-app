package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 客观题
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class ObjectiveQuestion<T> extends Question {
    /**
     * 选项列表
     */
    private List<Option> options;
    /**
     * 正确答案列表
     */
    private T rightAnswer;
}
