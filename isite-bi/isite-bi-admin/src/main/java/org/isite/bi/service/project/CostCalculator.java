package org.isite.bi.service.project;

import org.apache.commons.collections4.CollectionUtils;
import org.isite.bi.data.enums.project.CostType;
import org.isite.bi.data.vo.project.CostIndex;
import org.isite.bi.data.vo.project.CostRule;
import org.isite.bi.data.vo.project.CostSubject;
import org.isite.bi.data.vo.project.ProjectCost;
import org.isite.bi.po.project.CostRulePo;
import org.isite.commons.cloud.converter.DataConverter;
import org.isite.commons.cloud.converter.TreeConverter;
import org.isite.commons.lang.utils.TypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * @Description 计算费用
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class CostCalculator {
    private CostRuleService costRuleService;
    private CostArithmeticFactory costArithmeticFactory;

    @Autowired
    public void setCostRuleService(CostRuleService costRuleService) {
        this.costRuleService = costRuleService;
    }

    @Autowired
    public void setCostArithmeticFactory(CostArithmeticFactory costArithmeticFactory) {
        this.costArithmeticFactory = costArithmeticFactory;
    }

    public <S extends CostSubject> void execute(
            CostType costType, ProjectCost projectCost) {
        CostArithmetic<S, ?> costArithmetic = TypeUtils.cast(costArithmeticFactory.get(costType));
        List<S> costSubjects = costArithmetic.findCostSubject(projectCost);
        if (CollectionUtils.isEmpty(costSubjects)) {
            return;
        }
        List<CostRulePo> costRulePos = costRuleService.findCostRules(costType);
        if (CollectionUtils.isEmpty(costRulePos)) {
            costArithmetic.sumCostSubjects(costSubjects);
        } else {
            List<CostRule> costRules = TreeConverter.toTree(costRulePos, po -> DataConverter.convert(po, CostRule::new));
            List<CostIndex> costIndices = costRuleService.matches(costSubjects, costRules);
            if(CollectionUtils.isNotEmpty(costIndices)) {
                costArithmetic.sumCostIndexPairs(costIndices, costRules);
            }
        }
    }
}
