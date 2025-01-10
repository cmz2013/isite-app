package org.isite.oa.service;

import org.isite.mybatis.service.PoService;
import org.isite.oa.mapper.AttendanceRecordMapper;
import org.isite.oa.po.AttendanceRecordPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class AttendanceRecordService extends PoService<AttendanceRecordPo, Long> {

    @Autowired
    public AttendanceRecordService(AttendanceRecordMapper mapper) {
        super(mapper);
    }
}
