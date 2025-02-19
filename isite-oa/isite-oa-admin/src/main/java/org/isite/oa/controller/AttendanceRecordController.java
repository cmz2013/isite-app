package org.isite.oa.controller;

import org.isite.commons.cloud.data.op.Add;
import org.isite.commons.cloud.data.vo.Result;
import org.isite.commons.web.controller.BaseController;
import org.isite.oa.converter.AttendanceRecordConverter;
import org.isite.oa.data.constants.OaUrls;
import org.isite.oa.data.dto.AttendanceRecordDto;
import org.isite.oa.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @Author <font color='blue'>zhangcm</font>
 */
@RestController
public class AttendanceRecordController extends BaseController {
    private AttendanceRecordService attendanceRecordService;

    /**
     * 前端解析excel文件，导入考勤数据
     */
    @PostMapping(OaUrls.URL_OA + "/attendance/{attendancePeriod}/records")
    public Result<?> addAttendanceRecords(
            @PathVariable("attendancePeriod") Integer attendancePeriod,
            @Validated(Add.class) @RequestBody List<AttendanceRecordDto> attendanceRecordDtos) {
        return toResult(() -> attendanceRecordService.addAttendanceRecords(
                AttendanceRecordConverter.toAttendanceRecordPos(attendancePeriod, attendanceRecordDtos)));
    }

    @Autowired
    public void setAttendanceRecordService(AttendanceRecordService attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
    }
}
