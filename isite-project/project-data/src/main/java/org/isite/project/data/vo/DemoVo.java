package org.isite.project.data.vo;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.lang.data.Vo;

/**
 * VO数据示例
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class DemoVo extends Vo<Integer> {

    private String body;

    private String[] keys;
}
