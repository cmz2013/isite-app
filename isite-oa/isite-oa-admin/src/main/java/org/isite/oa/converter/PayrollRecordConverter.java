package org.isite.oa.converter;

import org.isite.oa.po.PayrollRecordPo;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
public class PayrollRecordConverter {

    private PayrollRecordConverter() {
    }

    public static PayrollRecordPo toPayrollRecordSelectivePo(Long employeeId, int payPeriod) {
        PayrollRecordPo payrollRecordPo = new PayrollRecordPo();
        payrollRecordPo.setEmployeeId(employeeId);
        payrollRecordPo.setPayPeriod(payPeriod);
        return payrollRecordPo;
    }
}
