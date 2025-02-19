package org.isite.oa.service;

import org.isite.mybatis.service.PoService;
import org.isite.oa.converter.AttendanceRecordConverter;
import org.isite.oa.mapper.AttendanceRecordMapper;
import org.isite.oa.po.AttendanceRecordPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class AttendanceRecordService extends PoService<AttendanceRecordPo, Long> {

    @Autowired
    public AttendanceRecordService(AttendanceRecordMapper mapper) {
        super(mapper);
    }

    /**
     * 添加或更新考勤记录
     */
    @Transactional(rollbackFor = Exception.class)
    public void addAttendanceRecords(List<AttendanceRecordPo> attendanceRecordPos) {
        attendanceRecordPos.forEach(attendanceRecordPo -> {
            AttendanceRecordPo oldPo = this.findOne(AttendanceRecordConverter.toAttendanceRecordSelectivePo(
                    attendanceRecordPo.getEmployeeId(), attendanceRecordPo.getAttendancePeriod()));
            if (null == oldPo) {
                this.insert(attendanceRecordPo);
            } else {
                attendanceRecordPo.setId(oldPo.getId());
                this.updateById(attendanceRecordPo);
            }
        });
    }

    /**
     * 批量（100条）查询考勤记录，ID除以shardTotal取余，如果余数为shardIndex，则返回该条记录
     */
    public List<AttendanceRecordPo> findList(int attendancePeriod, int shardIndex, int shardTotal, long minId) {
       return ((AttendanceRecordMapper) getMapper()).selectAttendanceRecords(attendancePeriod, shardIndex, shardTotal, minId);
    }
}
