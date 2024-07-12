package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.web.data.op.Add;
import org.isite.commons.web.data.op.Update;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author <font color='blue'>zhangcm</font>
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
