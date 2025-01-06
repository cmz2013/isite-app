package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.cloud.data.dto.TreeDto;

import javax.validation.constraints.NotBlank;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class MajorDto extends TreeDto<Integer> {
    /**
     * 专业名称
     */
    @NotBlank
    private String name;
}
