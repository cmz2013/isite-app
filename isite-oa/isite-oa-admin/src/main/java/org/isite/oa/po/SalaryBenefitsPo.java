package org.isite.oa.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;

import javax.persistence.Table;

/**
 * @Description 员工薪资福利
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "salary_benefits")
public class SalaryBenefitsPo extends Po<Integer> {
    /**
     * 员工ID
     */
    private Integer employeeId;

    /**
     * 基本工资(分)：¥15000/月
     */
     private Integer baseSalary;
    /**
     * 餐补(分)
     */
    private Integer mealAllowance;
    /**
     * 交通津贴(分)：¥500/月
     */
    private Integer transportAllowance;
    /**
     * 住房津贴(分)：¥1000/月
     */
    private Integer houseAllowance;
    /**
     * 年终奖：1-3个月基本工资（根据公司业绩和个人绩效）
     */
    private Integer annualBonus;
    /**
     * 公积金缴纳比例
     */
    private Integer accumulationFund;
    /**
     * 社保缴纳比例
     */
    private Integer socialSecurity;
    /**
     * 年假：15天/年
     */
    private Integer annualLeave;
    /**
     * 试用期薪资(分)：¥14,000/月（试用期3个月）
     */
    private Integer trialSalary;
}
