package org.isite.oa.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;
import org.isite.mybatis.type.EnumTypeHandler;
import org.isite.oa.data.enums.DeductionType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Description 扣款记录，通常用于支付税款、社保、医保等
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "deduction_record")
public class DeductionRecordPo extends Po<Long> {
    /**
     * 员工ID
     */
    private Integer employeeId;
    /**
     * 薪资周期
     */
    private Date payPeriod;
    /**
     * 扣除类型
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private DeductionType deductionType;
    /**
     * 扣除金额(分)
     */
    private Integer amount;
    /**
     * 备注
     */
    private String remark;
}
