package org.isite.exam.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.TreeModel;

import javax.persistence.Table;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "major")
public class MajorPo extends TreeModel<Integer> {
    /**
     * 专业名称
     */
    private String name;
}
