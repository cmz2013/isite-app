package org.isite.oa.converter;

import org.isite.oa.po.BonusRecordPo;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
public class BonusRecordConverter {

    private BonusRecordConverter() {
    }

    public static BonusRecordPo toBonusRecordSelectivePo(Long employeeId, Integer payPeriod) {
        BonusRecordPo bonusRecordPo = new BonusRecordPo();
        bonusRecordPo.setEmployeeId(employeeId);
        bonusRecordPo.setPayPeriod(payPeriod);
        return bonusRecordPo;
    }
}
