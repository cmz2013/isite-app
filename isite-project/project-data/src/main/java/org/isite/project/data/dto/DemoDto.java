package org.isite.project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.isite.commons.web.data.Dto;

import javax.validation.constraints.NotEmpty;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemoDto extends Dto<Integer> {

    private String body;

    @NotEmpty
    private String[] keys;
}
