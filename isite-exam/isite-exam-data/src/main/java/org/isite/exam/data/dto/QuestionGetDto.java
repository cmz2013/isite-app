package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.cloud.data.dto.Dto;
import org.isite.exam.data.enums.QuestionType;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class QuestionGetDto extends Dto<Long> {
    /**
     * 专业ID
     */
    private Integer majorId;
    /**
     * 题型
     */
    private QuestionType questionType;
    /**
     * 标签
     */
    private String tags;
    /**
     * 题库ID
     */
    private Integer poolId;
}
