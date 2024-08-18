package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.cloud.data.op.Add;
import org.isite.commons.cloud.data.op.Update;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class ObjectiveQuestionDto extends QuestionDto {
    /**
     * 选项列表
     */
    @NotEmpty(groups = {Add.class, Update.class})
    private List<OptionDto> options;
}
