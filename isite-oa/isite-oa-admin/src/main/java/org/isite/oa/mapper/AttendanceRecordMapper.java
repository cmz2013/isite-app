package org.isite.oa.mapper;

import org.apache.ibatis.annotations.Param;
import org.isite.mybatis.mapper.PoMapper;
import org.isite.oa.po.AttendanceRecordPo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Repository
public interface AttendanceRecordMapper extends PoMapper<AttendanceRecordPo, Long> {
    /**
     * 批量（100条）查询考勤记录，ID除以shardTotal取余，如果余数为shardIndex，则返回该条记录
     */
    List<AttendanceRecordPo> selectAttendanceRecords(
            @Param("attendancePeriod") int attendancePeriod, @Param("shardIndex") int shardIndex,
            @Param("shardTotal") int shardTotal, @Param("minId") long minId);
}
