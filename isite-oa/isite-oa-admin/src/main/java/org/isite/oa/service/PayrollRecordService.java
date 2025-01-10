package org.isite.oa.service;

import org.isite.mybatis.service.PoService;
import org.isite.oa.mapper.PayrollRecordMapper;
import org.isite.oa.po.PayrollRecordPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class PayrollRecordService extends PoService<PayrollRecordPo, Long> {

    @Autowired
    public PayrollRecordService(PayrollRecordMapper mapper) {
        super(mapper);
    }
}
