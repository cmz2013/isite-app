package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.cloud.data.op.Update;

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
    @NotNull(groups = Update.class)
    private Long questionId;
    /**
     * 用户答案
     */
    private String answer;
}
