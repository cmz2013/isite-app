package org.isite.oa.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;

import javax.persistence.Table;

/**
 * @Description 员工考勤记录
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "attendance_record")
public class AttendanceRecordPo extends Po<Long> {
    /**
     * 员工ID
     */
    private Long employeeId;
    /**
     * 考勤月份
     */
    private Integer attendancePeriod;
    /**
     * 应出勤天数
     */
    private Integer shouldDays;
    /**
     * 正常出勤天数
     */
    private Integer normalDays;
    /**
     * 异常出勤天数
     */
    private Integer abnormalDays;
    /**
     * 旷工天数
     */
    private Integer absentDays;
    /**
     * 应出勤时长（分钟）
     */
    private Integer shouldMinutes;
    /**
     * 实际出勤时长（分钟）
     */
    private Integer actualMinutes;
    /**
     * 迟到次数
     */
    private Integer lateTimes;
    /**
     * 迟到时长（分钟）
     */
    private Integer lateMinutes;
    /**
     * 早退次数
     */
    private Integer earlyTimes;
    /**
     * 早退时长（分钟）
     */
    private Integer earlyMinutes;
    /**
     * 加班时长（分钟）
     */
    private Integer overtimeMinutes;
    /**
     * 备注
     */
    private String remark;
}
