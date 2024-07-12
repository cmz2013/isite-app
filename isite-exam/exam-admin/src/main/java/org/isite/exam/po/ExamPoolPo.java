package org.isite.exam.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Model;

import javax.persistence.Table;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "exam_pool")
public class ExamPoolPo extends Model<Integer> {
    /**
     * 题库名称
     */
    private String title;
    /**
     * 备注
     */
    private String remark;
    /**
     * 租户ID
     */
    private Integer tenantId;
}
