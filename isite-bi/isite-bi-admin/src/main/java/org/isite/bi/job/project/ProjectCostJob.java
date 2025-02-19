package org.isite.bi.job.project;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.apache.commons.collections4.CollectionUtils;
import org.isite.bi.data.enums.project.CostType;
import org.isite.bi.data.vo.project.ProjectCost;
import org.isite.bi.service.project.CostCalculator;
import org.isite.bi.service.project.ProjectCostService;
import org.isite.commons.lang.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @Description 计算项目费用JOB
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class ProjectCostJob {
    private CostCalculator costCalculator;
    private ProjectCostService projectCostService;

    /**
     * @Description 分片任务：计算项目费用
     * 10个执行器的集群来处理10w条数据，每台机器只需要处理1w条数据，耗时降低10倍
     */
    @XxlJob("projectCostJob")
    public ReturnT<String> execute(String params) {
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        XxlJobHelper.log("项目费用计算任务：" +
                "当前分片序号(分片序号从0开始) = {}, 总分片数 = {}", shardIndex, shardTotal);
        long index = Constants.ZERO;
        //按CostType中order从小到大的顺序返回
        List<CostType> costTypes = Arrays.stream(CostType.values())
                .sorted(Comparator.comparingInt(CostType::getOrder)).collect(Collectors.toList());
        List<ProjectCost> projectCosts = projectCostService.findList(shardIndex, shardTotal, Constants.ZERO);
        while (CollectionUtils.isNotEmpty(projectCosts)) {
            projectCosts.forEach(projectCost -> costTypes.forEach(costType -> costCalculator.execute(costType, projectCost)));
            projectCosts = projectCosts.size() == Constants.HUNDRED ?
                    projectCostService.findList(shardIndex, shardTotal, ++index * Constants.HUNDRED) : null;
        }
        return ReturnT.SUCCESS;
    }

    @Autowired
    public void setCostCalculator(CostCalculator costCalculator) {
        this.costCalculator = costCalculator;
    }

    @Autowired
    public void setProjectCostService(ProjectCostService projectCostService) {
        this.projectCostService = projectCostService;
    }
}
