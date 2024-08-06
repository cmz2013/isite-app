package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.web.data.op.Add;
import org.isite.commons.web.data.op.Update;

import javax.validation.constraints.NotNull;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class SingleChoiceDto extends ObjectiveQuestionDto {
    /**
     * 正确答案选项序号
     */
    @NotNull(groups = {Add.class, Update.class})
    private Integer rightAnswer;
}
