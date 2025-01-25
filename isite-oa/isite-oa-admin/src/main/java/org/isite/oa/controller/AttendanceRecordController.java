package org.isite.oa.controller;

import org.isite.commons.cloud.data.op.Add;
import org.isite.commons.cloud.data.vo.Result;
import org.isite.commons.web.controller.BaseController;
import org.isite.oa.data.dto.AttendanceRecordDto;
import org.isite.oa.po.AttendanceRecordPo;
import org.isite.oa.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.isite.commons.cloud.converter.DataConverter.convert;
import static org.isite.oa.data.constants.UrlConstants.URL_OA;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@RestController
public class AttendanceRecordController extends BaseController {

    private AttendanceRecordService attendanceRecordService;

    /**
     * 前端解析excel文件，导入异常考勤数据
     */
    @PostMapping(URL_OA + "/attendance/records")
    public Result<Integer> addAttendanceRecords(@Validated(Add.class) @RequestBody List<AttendanceRecordDto> attendanceRecordDtos) {
        return toResult(attendanceRecordService.insert(convert(attendanceRecordDtos, AttendanceRecordPo::new)));
    }

    @Autowired
    public void setAttendanceRecordService(AttendanceRecordService attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
    }
}
