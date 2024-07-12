package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 题干
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class QuestionStem implements Serializable {
    /**
     * 标题
     */
    private String title;
    /**
     * 图片
     */
    private String image;
}
