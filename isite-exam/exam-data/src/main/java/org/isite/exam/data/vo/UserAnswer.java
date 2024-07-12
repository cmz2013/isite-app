package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户答案
 *
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class UserAnswer implements Serializable {
    /**
     * 题目ID
     */
    private Long questionId;
    /**
     * 用户答案
     */
    private String answer;
    /**
     * 用户得分
     */
    private Integer score;
}
