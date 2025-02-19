package org.isite.oa.converter;

import org.apache.commons.collections4.CollectionUtils;
import org.isite.commons.cloud.converter.DataConverter;
import org.isite.oa.data.dto.AttendanceRecordDto;
import org.isite.oa.po.AttendanceRecordPo;

import java.util.Collections;
import java.util.List;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
public class AttendanceRecordConverter {

    private AttendanceRecordConverter() {
    }

    public static List<AttendanceRecordPo> toAttendanceRecordPos(
            Integer attendancePeriod, List<AttendanceRecordDto> attendanceRecordDtos) {
        return CollectionUtils.isEmpty(attendanceRecordDtos) ? Collections.emptyList() :
                DataConverter.convert(attendanceRecordDtos, () -> {
            AttendanceRecordPo attendanceRecordPo = new AttendanceRecordPo();
            attendanceRecordPo.setAttendancePeriod(attendancePeriod);
            return attendanceRecordPo;
        });
    }

    public static AttendanceRecordPo toAttendanceRecordSelectivePo(Long employeeId, Integer attendancePeriod) {
        AttendanceRecordPo attendanceRecordPo = new AttendanceRecordPo();
        attendanceRecordPo.setEmployeeId(employeeId);
        attendanceRecordPo.setAttendancePeriod(attendancePeriod);
        return attendanceRecordPo;
    }
}
