package org.isite.project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.isite.commons.cloud.data.Dto;

import javax.validation.constraints.NotBlank;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemoDto extends Dto<Integer> {

    @NotBlank
    private String key;

    private String body;
}
