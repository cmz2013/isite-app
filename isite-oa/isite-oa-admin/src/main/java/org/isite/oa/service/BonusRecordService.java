package org.isite.oa.service;

import org.isite.mybatis.service.PoService;
import org.isite.oa.mapper.BonusRecordMapper;
import org.isite.oa.po.BonusRecordPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class BonusRecordService extends PoService<BonusRecordPo, Long> {

    @Autowired
    public BonusRecordService(BonusRecordMapper mapper) {
        super(mapper);
    }
}
