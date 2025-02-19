package org.isite.oa.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.commons.collections4.CollectionUtils;
import org.isite.commons.lang.Constants;
import org.isite.commons.lang.utils.DateUtils;
import org.isite.oa.converter.PayrollRecordConverter;
import org.isite.oa.po.AttendanceRecordPo;
import org.isite.oa.po.PayrollRecordPo;
import org.isite.oa.service.AttendanceRecordService;
import org.isite.oa.service.PayrollRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @Description 发放员工薪资JOB（每月5号执行），如果查询不到考勤记录，则不发放薪资
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class PayrollJob {
    private PayrollRecordService payrollRecordService;
    private AttendanceRecordService attendanceRecordService;

    /**
     * @Description 分片任务：发放员工薪资
     * 10个执行器的集群来处理10w条数据，每台机器只需要处理1w条数据，耗时降低10倍
     */
    @XxlJob("payrollJob")
    public ReturnT<String> execute(String params) {
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        XxlJobHelper.log("发放员工薪资任务：" +
                "当前分片序号(分片序号从0开始) = {}, 总分片数 = {}", shardIndex, shardTotal);
        long index = Constants.ZERO;
        LocalDateTime attendanceStartTime = DateUtils.startOfMonth(LocalDateTime.now().minusMonths(Constants.ONE));
        LocalDateTime attendanceEndTime = DateUtils.endOfMonth(attendanceStartTime);
        int payPeriod = Integer.parseInt(DateUtils.format(attendanceStartTime, DateUtils.PATTERN_MONTH));
        //查询考勤记录，考勤记录不存在不发薪（必须在发薪之前同步考勤记录）
        List<AttendanceRecordPo> attendanceRecords = attendanceRecordService.findList(
                payPeriod, shardIndex, shardTotal, Constants.ZERO);
        while (CollectionUtils.isNotEmpty(attendanceRecords)) {
            attendanceRecords.forEach(attendanceRecordPo -> {
                PayrollRecordPo payrollRecordPo = PayrollRecordConverter.toPayrollRecordSelectivePo(attendanceRecordPo.getEmployeeId(), payPeriod);
                if (!payrollRecordService.exists(payrollRecordPo)) {
                    payrollRecordService.addPayrollRecord(attendanceStartTime, attendanceEndTime, attendanceRecordPo);
                }
            });
            attendanceRecords = attendanceRecords.size() == Constants.HUNDRED ?
                    attendanceRecordService.findList(payPeriod, shardIndex, shardTotal, ++index * Constants.HUNDRED) : null;
        }
        return ReturnT.SUCCESS;
    }

    @Autowired
    public void setPayrollRecordService(PayrollRecordService payrollRecordService) {
        this.payrollRecordService = payrollRecordService;
    }

    @Autowired
    public void setAttendanceRecordService(AttendanceRecordService attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
    }
}
