package org.isite.oa.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Description 员工发薪记录
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "payroll_record")
public class PayrollRecordPo extends Po<Long> {
    /**
     * 员工ID
     */
    private Integer employeeId;
    /**
     * 薪资周期
     */
    private Date payPeriod;
    /**
     * 基本工资(分)
     */
    private Integer basicSalary;
    /**
     * 津贴（分），如交通津贴、住房津贴等
     */
    private Integer allowances;
    /**
     * 加班费(分)
     */
    private Integer overtimePay;
    /**
     * 奖金(分)
     */
    private Integer bonuses;
    /**
     * 应发工资(分)=基本工资 + 津贴 + 奖金 + 加班费
     */
    private Integer grossSalary;
    /**
     * 扣除项(分)
     */
    private Integer deductions;
    /**
     * 实发工资(分)=应发工资 - 扣款
     */
    private Integer netSalary;
    /**
     * 发薪日期
     */
    private Date paymentDate;
    /**
     * 备注
     */
    private String remark;
}
