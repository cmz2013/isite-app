package org.isite.oa.service;

import org.isite.mybatis.service.PoService;
import org.isite.oa.mapper.DeductionRecordMapper;
import org.isite.oa.po.DeductionRecordPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class DeductionRecordService extends PoService<DeductionRecordPo, Long> {

    @Autowired
    public DeductionRecordService(DeductionRecordMapper mapper) {
        super(mapper);
    }
}
