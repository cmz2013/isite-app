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
    private Long employeeId;
    /**
     * 基本工资(分)：¥15000/月
     */
     private Long baseSalary;
    /**
     * 餐补(分)
     */
    private Long mealAllowance;
    /**
     * 交通津贴(分)：¥500/月
     */
    private Long transportAllowance;
    /**
     * 住房津贴(分)：¥1000/月
     */
    private Long houseAllowance;
    /**
     * 年终奖：1-3个月基本工资（根据公司业绩和个人绩效）
     */
    private Long annualBonus;
    /**
     * 公积金缴纳比例（百分比）
     */
    private Integer providentFund;
    /**
     * 养老保险缴纳比例（百分比）
     */
    private Integer pensionInsurance;
    /**
     * 医疗保险缴纳比例（百分比）
     */
    private Integer medicalInsurance;
    /**
     * 失业保险保险缴纳比例（百分比）
     */
    private Integer unemploymentInsurance;
    /**
     * 工伤保险缴纳比例（百分比）
     */
    private Integer workInjuryInsurance;
    /**
     * 生育保险缴纳比例（百分比）
     */
    private Integer maternityInsurance;
    /**
     * 年假：15天/年
     */
    private Integer annualLeave;
    /**
     * 试用期薪资(分)：¥14,000/月（试用期3个月）
     */
    private Long trialSalary;
}
