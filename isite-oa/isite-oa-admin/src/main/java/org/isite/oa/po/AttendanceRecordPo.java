package org.isite.oa.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;
import org.isite.mybatis.type.EnumTypeHandler;
import org.isite.oa.data.enums.AbsenceReason;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Description 员工异常考勤记录
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "attendance_record")
public class AttendanceRecordPo extends Po<Long> {
    /**
     * 员工ID
     */
    private Integer employeeId;
    /**
     * 考勤日期
     */
    private Date attendanceDate;
    /**
     * 签到时间
     */
    private Date checkInTime;
    /**
     * 签退时间
     */
    private Date checkOutTime;
    /**
     * 缺勤原因(年假、事假、旷工)
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private AbsenceReason absenceReason;
    /**
     * 加班时间
     */
    private Integer overtimeHours;
    /**
     * 备注
     */
    private String remark;
}
