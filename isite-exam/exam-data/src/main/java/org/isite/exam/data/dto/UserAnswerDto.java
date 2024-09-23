package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @Description 答题记录
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class UserAnswerDto {
    /**
     * 题目ID
     */
    @NotNull
    private Long questionId;
    /**
     * 用户答案
     */
    private String answer;
}
