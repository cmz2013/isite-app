package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.web.data.op.Add;
import org.isite.commons.web.data.op.Update;

import javax.validation.constraints.NotBlank;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class QuestionStemDto {
    /**
     * 标题
     */
    @NotBlank(groups = {Add.class, Update.class})
    private String title;
    /**
     * 图片
     */
    private String image;
}
