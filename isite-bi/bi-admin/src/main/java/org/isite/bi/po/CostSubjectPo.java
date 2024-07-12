package org.isite.bi.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Model;

import javax.persistence.Table;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "cost_subject")
public class CostSubjectPo extends Model<Integer> {
}
