package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class MultipleChoiceDto extends ObjectiveQuestionDto {
    /**
     * 正确答案
     */
    @NotEmpty
    private Set<Integer> rightAnswer;
}
