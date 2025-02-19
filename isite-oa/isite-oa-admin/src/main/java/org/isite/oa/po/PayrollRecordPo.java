package org.isite.oa.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;

import javax.persistence.Table;
import java.time.LocalDateTime;
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
    private Long employeeId;
    /**
     * 薪资周期 yyyyMM
     */
    private Integer payPeriod;
    /**
     * 基本工资(分)
     */
    private Long basicSalary;
    /**
     * 津贴（分），如交通津贴、住房津贴等
     */
    private Long allowances;
    /**
     * 加班费(分)
     */
    private Long overtimePay;
    /**
     * 奖金(分)
     */
    private Long bonuses;
    /**
     * 应发工资(分)=基本工资 + 津贴 + 奖金 + 加班费
     */
    private Long grossSalary;
    /**
     * 扣除项(分)
     */
    private Long deductions;
    /**
     * 实发工资(分)=应发工资 - 扣款
     */
    private Long netSalary;
    /**
     * 发薪时间
     */
    private LocalDateTime paymentTime;
    /**
     * 备注
     */
    private String remark;
}
