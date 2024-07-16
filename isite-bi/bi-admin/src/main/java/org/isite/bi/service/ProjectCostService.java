package org.isite.bi.service;

import org.isite.bi.cost.CostCalculator;
import org.isite.bi.data.enums.CostType;
import org.isite.bi.mapper.ProjectCostMapper;
import org.isite.bi.po.ProjectCostPo;
import org.isite.mybatis.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Arrays.stream;
import static java.util.Comparator.comparingInt;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class ProjectCostService extends ModelService<ProjectCostPo, Integer> {

    private CostCalculator costCalculator;

    @Autowired
    public ProjectCostService(ProjectCostMapper mapper) {
        super(mapper);
    }

    @Autowired
    public void setCostCalculator(CostCalculator costCalculator) {
        this.costCalculator = costCalculator;
    }

    /**
     * 项目费用计算批处理
     */
    public void computeCost(int curCostId, int shardTotal, int maxCostId) {
        while (curCostId <= maxCostId) {
            ProjectCostPo projectCostPo = get(curCostId);
            if (null != projectCostPo) {
                //按CostType中order从小到大的顺序返回
                stream(CostType.values()).sorted(comparingInt(CostType::getOrder)).forEach(
                        costType -> costCalculator.execute(projectCostPo, costType));
            }
            curCostId += shardTotal;
        }
    }

    public int getMaxCostId() {
        //TODO
        return 3;
    }
}