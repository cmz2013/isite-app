package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.web.data.Dto;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description 考试记录DTO
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class ExamRecordDto extends Dto<Long> {
    /**
     * 答题记录
     */
    @Valid
    private List<UserAnswerDto> userAnswers;
}
