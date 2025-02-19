package org.isite.oa.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.cloud.data.dto.Dto;

import javax.validation.constraints.NotNull;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class AttendanceRecordDto extends Dto {
    /**
     * 员工ID
     */
    @NotNull
    private Long employeeId;
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
    @NotNull
    private Integer shouldMinutes;
    /**
     * 实际出勤时长（分钟）
     */
    @NotNull
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
