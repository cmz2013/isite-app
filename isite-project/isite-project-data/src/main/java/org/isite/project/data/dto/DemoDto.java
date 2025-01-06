package org.isite.project.data.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.isite.commons.cloud.data.dto.Dto;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemoDto extends Dto<Integer> {

    private String field1;
    private String field2;
}
