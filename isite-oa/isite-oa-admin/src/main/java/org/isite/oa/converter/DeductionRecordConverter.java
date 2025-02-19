package org.isite.oa.converter;

import org.isite.commons.lang.Constants;
import org.isite.oa.data.enums.DeductionSubject;
import org.isite.oa.po.DeductionRecordPo;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
public class DeductionRecordConverter {

    private DeductionRecordConverter() {
    }

    public static DeductionRecordPo toDeductionRecordSelectivePo(Long employeeId, Integer payPeriod) {
        DeductionRecordPo deductionRecordPo = new DeductionRecordPo();
        deductionRecordPo.setEmployeeId(employeeId);
        deductionRecordPo.setPayPeriod(payPeriod);
        return deductionRecordPo;
    }

    public static DeductionRecordPo toDeductionRecordPo(
            long employeeId, Integer payPeriod, DeductionSubject subject, long amount) {
        DeductionRecordPo deductionRecordPo = new DeductionRecordPo();
        deductionRecordPo.setEmployeeId(employeeId);
        deductionRecordPo.setPayPeriod(payPeriod);
        deductionRecordPo.setDeductionSubject(subject);
        deductionRecordPo.setAmount(amount);
        deductionRecordPo.setRemark(Constants.BLANK_STR);
        return deductionRecordPo;
    }
}
