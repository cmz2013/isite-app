package org.isite.oa.service;

import org.apache.commons.collections4.CollectionUtils;
import org.isite.commons.lang.Assert;
import org.isite.commons.lang.Constants;
import org.isite.commons.lang.utils.DateUtils;
import org.isite.commons.lang.utils.TypeUtils;
import org.isite.commons.web.sign.SignSecret;
import org.isite.mybatis.service.PoService;
import org.isite.oa.config.SalaryTaxConfig;
import org.isite.oa.converter.BonusRecordConverter;
import org.isite.oa.converter.DeductionRecordConverter;
import org.isite.oa.data.constants.OaConstants;
import org.isite.oa.data.enums.DeductionSubject;
import org.isite.oa.mapper.PayrollRecordMapper;
import org.isite.oa.po.AttendanceRecordPo;
import org.isite.oa.po.BankAccountPo;
import org.isite.oa.po.BonusRecordPo;
import org.isite.oa.po.DeductionRecordPo;
import org.isite.oa.po.PayrollRecordPo;
import org.isite.oa.po.SalaryBenefitsPo;
import org.isite.tenant.client.EmployeeAccessor;
import org.isite.tenant.data.constants.TenantConstants;
import org.isite.tenant.data.vo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class PayrollRecordService extends PoService<PayrollRecordPo, Long> {
    private SignSecret signSecret;
    private BonusRecordService bonusRecordService;
    private DeductionRecordService deductionRecordService;
    private SalaryBenefitsService salaryBenefitsService;
    private SalaryTaxConfig salaryTaxConfig;
    private BankAccountService bankAccountService;

    @Autowired
    public PayrollRecordService(PayrollRecordMapper mapper) {
        super(mapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addPayrollRecord(LocalDateTime attendanceStartTime, LocalDateTime attendanceEndTime,
                                 AttendanceRecordPo attendanceRecordPo) {
        Employee employee = EmployeeAccessor.getEmployee(
                attendanceRecordPo.getEmployeeId(), signSecret.password(TenantConstants.SERVICE_ID));
        SalaryBenefitsPo salaryBenefitsPo = salaryBenefitsService.findOne(SalaryBenefitsPo::getEmployeeId, employee.getId());
        PayrollRecordPo payrollRecordPo = new PayrollRecordPo();
        payrollRecordPo.setEmployeeId(employee.getId());
        payrollRecordPo.setPayPeriod(attendanceRecordPo.getAttendancePeriod());
        payrollRecordPo.setBasicSalary(getBasicSalary(employee, salaryBenefitsPo, attendanceStartTime, attendanceEndTime));
        payrollRecordPo.setAllowances(salaryBenefitsPo.getHouseAllowance() +
                salaryBenefitsPo.getTransportAllowance() + salaryBenefitsPo.getMealAllowance());
        payrollRecordPo.setBonuses(getBonuses(employee.getId(), payrollRecordPo.getPayPeriod()));
        //先乘后除，避免浮点数精度问题
        payrollRecordPo.setOvertimePay(payrollRecordPo.getBasicSalary() *
                attendanceRecordPo.getOvertimeMinutes() / attendanceRecordPo.getShouldMinutes());
        payrollRecordPo.setGrossSalary(payrollRecordPo.getBasicSalary() + payrollRecordPo.getAllowances() +
                payrollRecordPo.getBonuses() + payrollRecordPo.getOvertimePay());
        payrollRecordPo.setDeductions(getDeductions(employee, salaryBenefitsPo, attendanceRecordPo, payrollRecordPo));
        payrollRecordPo.setNetSalary(payrollRecordPo.getGrossSalary() - payrollRecordPo.getDeductions());
        Assert.isTrue(payrollRecordPo.getNetSalary() < Constants.ZERO, "netSalary cannot be negative");
        payrollRecordPo.setPaymentTime(LocalDateTime.now());
        //先本地持久化，再调用远程服务接口，远程接口失败回滚本地事务
        this.insert(payrollRecordPo);
        if (payrollRecordPo.getNetSalary() > Constants.ZERO) {
            BankAccountPo bankAccount = bankAccountService.findOne(BankAccountPo::getEmployeeId, employee.getId());
            //TODO 通过财务系统接口发放薪资，发放失败事务回滚
        }
    }

    /**
     * 计算员工月薪，正常休假天数按正常考勤天数计算
     */
    private long getBasicSalary(Employee employee, SalaryBenefitsPo salaryBenefitsPo,
                                LocalDateTime attendanceStartTime, LocalDateTime attendanceEndTime) {
        long trialDays = getTrialDays(employee, attendanceStartTime, attendanceEndTime);
        long attendanceDays = attendanceEndTime.getDayOfMonth();
        //先乘后除，避免浮点数精度问题
        return salaryBenefitsPo.getBaseSalary() * (attendanceDays - trialDays) / attendanceDays +
                salaryBenefitsPo.getTrialSalary() * trialDays / attendanceDays;
    }

    /**
     * 计算员工在考勤月的试用天数
     */
    private int getTrialDays(Employee employee, LocalDateTime attendanceStartTime, LocalDateTime attendanceEndTime) {
        LocalDateTime trialEndTime = employee.getHireDate().plusMonths(employee.getTrialPeriod()).atTime(LocalTime.MAX);
        //考勤开始时间在员工试用结束日期之后，试用天数为0
        if (attendanceStartTime.isAfter(trialEndTime)) {
            return Constants.ZERO;
        }
        //考勤结束时间在员工试用结束日期之前，试用天数为考勤天数
        if (attendanceEndTime.isBefore(trialEndTime)) {
            return attendanceEndTime.getDayOfMonth();
        }
        return TypeUtils.cast(attendanceEndTime.getDayOfMonth() - DateUtils.getDays(trialEndTime, attendanceEndTime));
    }

    private long getDeductions(Employee employee, SalaryBenefitsPo salaryBenefitsPo,
                               AttendanceRecordPo attendanceRecordPo, PayrollRecordPo payrollRecordPo) {
        List<DeductionRecordPo> deductionRecordPos = deductionRecordService.findList(
                DeductionRecordConverter.toDeductionRecordSelectivePo(employee.getId(), payrollRecordPo.getPayPeriod()));
        deductionRecordPos.addAll(getBenefitDeductions(
                employee, salaryBenefitsPo, payrollRecordPo.getPayPeriod(), payrollRecordPo.getBasicSalary()));

        int absentMinutes = attendanceRecordPo.getShouldMinutes() - attendanceRecordPo.getActualMinutes();
        if(absentMinutes > Constants.ZERO) {
            deductionRecordPos.add(getAttendanceDeduction(employee, attendanceRecordPo, payrollRecordPo, absentMinutes));
        }
        long deductions = deductionRecordPos.stream().mapToLong(DeductionRecordPo::getAmount).sum();
        long taxableIncome = payrollRecordPo.getGrossSalary() - deductions - salaryTaxConfig.getTaxFree();
        return taxableIncome > Constants.ZERO ? deductions :
                deductions + getTaxDeduction(employee, payrollRecordPo, taxableIncome).getAmount();
    }

    /**
     * 生成税收扣费记录
     */
    private DeductionRecordPo getTaxDeduction(Employee employee, PayrollRecordPo payrollRecordPo, long taxableIncome) {
        DeductionRecordPo deductionRecordPo = DeductionRecordConverter.toDeductionRecordPo(
                employee.getId(), payrollRecordPo.getPayPeriod(), DeductionSubject.INCOME_TAX,
                taxableIncome * salaryTaxConfig.getTaxRate(taxableIncome) / Constants.HUNDRED);
        deductionRecordService.insert(deductionRecordPo);
        return deductionRecordPo;
    }

    /**
     * 根据异常考勤生成扣费记录
     */
    private DeductionRecordPo getAttendanceDeduction(
            Employee employee, AttendanceRecordPo attendanceRecordPo, PayrollRecordPo payrollRecordPo, int absentMinutes) {
        DeductionRecordPo deductionRecordPo = DeductionRecordConverter.toDeductionRecordPo(
                employee.getId(), payrollRecordPo.getPayPeriod(), DeductionSubject.ABSENTEEISM,
                payrollRecordPo.getBasicSalary() * absentMinutes / attendanceRecordPo.getShouldMinutes());
        deductionRecordService.insert(deductionRecordPo);
        return deductionRecordPo;
    }

    /**
     * 生成五险一金扣除记录
     */
    private List<DeductionRecordPo> getBenefitDeductions(
            Employee employee, SalaryBenefitsPo salaryBenefitsPo, int payPeriod, long basicSalary) {
        List<DeductionRecordPo> deductionRecordPos = new ArrayList<>();
        if (Integer.parseInt(DateUtils.format(employee.getHireDate(), DateUtils.PATTERN_MONTH)) == payPeriod &&
                employee.getHireDate().getDayOfMonth() < OaConstants.MIN_DAYS_EMPLOYEE_BENEFIT) {
            return deductionRecordPos;
        }
        DeductionRecordPo deductionRecordPo = DeductionRecordConverter.toDeductionRecordPo(
                employee.getId(), payPeriod, DeductionSubject.PROVIDENT_FUND,
                basicSalary * salaryBenefitsPo.getProvidentFund() / Constants.HUNDRED);
        deductionRecordService.insert(deductionRecordPo);
        deductionRecordPos.add(deductionRecordPo);

        deductionRecordPo = DeductionRecordConverter.toDeductionRecordPo(
                employee.getId(), payPeriod, DeductionSubject.MEDICAL_INSURANCE,
                basicSalary * salaryBenefitsPo.getMedicalInsurance() / Constants.HUNDRED);
        deductionRecordService.insert(deductionRecordPo);
        deductionRecordPos.add(deductionRecordPo);

        deductionRecordPo = DeductionRecordConverter.toDeductionRecordPo(
                employee.getId(), payPeriod, DeductionSubject.PENSION_INSURANCE,
                basicSalary * salaryBenefitsPo.getPensionInsurance() / Constants.HUNDRED);
        deductionRecordService.insert(deductionRecordPo);
        deductionRecordPos.add(deductionRecordPo);

        deductionRecordPo = DeductionRecordConverter.toDeductionRecordPo(
                employee.getId(), payPeriod, DeductionSubject.UNEMPLOYMENT_INSURANCE,
                basicSalary * salaryBenefitsPo.getUnemploymentInsurance() / Constants.HUNDRED);
        deductionRecordService.insert(deductionRecordPo);
        deductionRecordPos.add(deductionRecordPo);

        deductionRecordPo = DeductionRecordConverter.toDeductionRecordPo(
                employee.getId(), payPeriod, DeductionSubject.WORK_INJURY_INSURANCE,
                basicSalary * salaryBenefitsPo.getWorkInjuryInsurance() / Constants.HUNDRED);
        deductionRecordService.insert(deductionRecordPo);
        deductionRecordPos.add(deductionRecordPo);

        deductionRecordPo = DeductionRecordConverter.toDeductionRecordPo(
                employee.getId(), payPeriod, DeductionSubject.MATERNITY_INSURANCE,
                basicSalary * salaryBenefitsPo.getMaternityInsurance() / Constants.HUNDRED);
        deductionRecordService.insert(deductionRecordPo);
        deductionRecordPos.add(deductionRecordPo);
        return deductionRecordPos;
    }

    private long getBonuses(long employeeId, int payPeriod) {
        int bonuses = Constants.ZERO;
        List<BonusRecordPo> bonusRecords = bonusRecordService.findList(
                BonusRecordConverter.toBonusRecordSelectivePo(employeeId, payPeriod));
        if (CollectionUtils.isNotEmpty(bonusRecords)) {
            for (BonusRecordPo bonusRecord : bonusRecords) {
                bonuses += bonusRecord.getAmount();
            }
        }
        return bonuses;
    }

    @Autowired
    public void setSignSecret(SignSecret signSecret) {
        this.signSecret = signSecret;
    }

    @Autowired
    public void setBonusRecordService(BonusRecordService bonusRecordService) {
        this.bonusRecordService = bonusRecordService;
    }

    @Autowired
    public void setDeductionRecordService(DeductionRecordService deductionRecordService) {
        this.deductionRecordService = deductionRecordService;
    }

    @Autowired
    public void setSalaryBenefitsService(SalaryBenefitsService salaryBenefitsService) {
        this.salaryBenefitsService = salaryBenefitsService;
    }

    @Autowired
    public void setSalaryTaxConfig(SalaryTaxConfig salaryTaxConfig) {
        this.salaryTaxConfig = salaryTaxConfig;
    }

    @Autowired
    public void setBankAccountService(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }
}
