package org.isite.bi.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.isite.bi.service.ProjectCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.xxl.job.core.biz.model.ReturnT.SUCCESS;
import static com.xxl.job.core.context.XxlJobHelper.getShardIndex;
import static com.xxl.job.core.context.XxlJobHelper.getShardTotal;
import static com.xxl.job.core.context.XxlJobHelper.log;
import static org.isite.commons.lang.data.Constants.ONE;

/**
 * @Description 计算项目费用JOB
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class ProjectCostJob {

    private ProjectCostService projectCostService;

    /**
     * @Description 分片任务：计算项目费用
     * 10个执行器的集群来处理10w条数据，每台机器只需要处理1w条数据，耗时降低10倍
     */
    @XxlJob("projectCostJobHandler")
    public ReturnT<String> runProjectCost(String params) {
        int shardIndex = getShardIndex();
        int shardTotal = getShardTotal();
        log("项目费用计算任务：当前分片序号(分片序号从0开始) = {}, 总分片数 = {}", shardIndex, shardTotal);

        int maxCostId = projectCostService.getMaxCostId();
        int curCostId = shardIndex + ONE;
        projectCostService.runProjectCost(curCostId, shardTotal, maxCostId);
        return SUCCESS;
    }

    @Autowired
    public void setProjectCostService(ProjectCostService projectCostService) {
        this.projectCostService = projectCostService;
    }
}
