package org.isite.oa.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.isite.oa.service.PayrollRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xxl.job.core.biz.model.ReturnT.SUCCESS;
import static com.xxl.job.core.context.XxlJobHelper.getShardIndex;
import static com.xxl.job.core.context.XxlJobHelper.getShardTotal;
import static com.xxl.job.core.context.XxlJobHelper.log;
import static org.isite.commons.lang.Constants.ZERO;
import static org.isite.tenant.client.EmployeeAccessor.getEmployeeId;

/**
 * @Description 计算项目费用JOB
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class PayrollJob {

    private PayrollRecordService payrollRecordService;

    /**
     * @Description 分片任务：发放员工薪资
     * 10个执行器的集群来处理10w条数据，每台机器只需要处理1w条数据，耗时降低10倍
     */
    @XxlJob("payrollJob")
    public ReturnT<String> execute(String params) {
        int shardIndex = getShardIndex();
        int shardTotal = getShardTotal();
        log("发放员工薪资任务：当前分片序号(分片序号从0开始) = {}, 总分片数 = {}", shardIndex, shardTotal);

        Long employeeId = getEmployeeId(shardIndex, shardTotal, ZERO);
        while (null != employeeId) {
            payrollRecordService.addPayrollRecord(employeeId);
            employeeId = getEmployeeId(shardIndex, shardTotal, employeeId);
        }
        return SUCCESS;
    }

    @Autowired
    public void setPayrollRecordService(PayrollRecordService payrollRecordService) {
        this.payrollRecordService = payrollRecordService;
    }
}
