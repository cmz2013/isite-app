package org.isite.bi.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.isite.bi.cost.CostCalculator;
import org.isite.bi.data.enums.CostType;
import org.isite.bi.data.vo.CostElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.xxl.job.core.biz.model.ReturnT.SUCCESS;
import static com.xxl.job.core.context.XxlJobHelper.getShardIndex;
import static com.xxl.job.core.context.XxlJobHelper.getShardTotal;
import static com.xxl.job.core.context.XxlJobHelper.log;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.isite.bi.data.enums.CostType.values;
import static org.isite.commons.lang.data.Constants.ZERO;

/**
 * @Description 计算项目费用JOB
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class ProjectCostJob {

    private CostCalculator costCalculator;

    /**
     * @Description 分片任务：计算项目费用
     * 10个执行器的集群来处理10w条数据，每台机器只需要处理1w条数据，耗时降低10倍
     */
    @XxlJob("projectCostJobHandler")
    public ReturnT<String> execute(String params) {
        int shardIndex = getShardIndex();
        int shardTotal = getShardTotal();
        log("项目费用计算任务：当前分片序号(分片序号从0开始) = {}, 总分片数 = {}", shardIndex, shardTotal);

        //按CostType中order从小到大的顺序返回
        List<CostType> costTypes = stream(values()).sorted(comparingInt(CostType::getOrder)).collect(toList());
        List<CostElement> costElements = findCostElements(shardIndex, ZERO);
        while (isNotEmpty(costElements)) {
            costElements.forEach(costElement -> costTypes.forEach(costType -> costCalculator.execute(costType, costElement)));
            costElements = findCostElements(shardIndex, costElements.get(ZERO).getProjectId());
        }
        return SUCCESS;
    }

    /**
     * 根据shardIndex分片，查询一个项目的费用参数
     */
    private List<CostElement> findCostElements(int shardIndex, int minProjectId) {
        return null;
    }

    @Autowired
    public void setCostCalculator(CostCalculator costCalculator) {
        this.costCalculator = costCalculator;
    }

}
