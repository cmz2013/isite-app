package org.isite.bi.cost;

import org.isite.bi.data.enums.CostType;
import org.isite.bi.data.vo.CostRule;
import org.isite.bi.data.vo.CostSubject;
import org.isite.bi.po.CostRulePo;
import org.isite.bi.po.ProjectCostPo;
import org.isite.bi.service.CostRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.isite.commons.lang.utils.TypeUtils.cast;
import static org.isite.commons.web.data.Converter.convert;
import static org.isite.jpa.converter.TreeConverter.toTree;

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

    public <S extends CostSubject> void execute(ProjectCostPo projectCostPo, CostType costType) {
        CostArithmetic<S, ?> costArithmetic = cast(costArithmeticFactory.get(costType));
        List<S> costSubjects = costArithmetic.findCostSubject(projectCostPo);
        if (isEmpty(costSubjects)) {
            return;
        }
        List<CostRulePo> costRulePos = costRuleService.findCostRules(costType);
        if (isEmpty(costRulePos)) {
            costArithmetic.sumCostSubjects(costSubjects);
        } else {
            List<CostRule> costRules = toTree(costRulePos, po -> convert(po, CostRule::new));
            List<CostIndexPair> costIndexPairs = costRuleService.matches(costSubjects, costRules);
            if(isNotEmpty(costIndexPairs)) {
                costArithmetic.sumCostIndexPairs(costIndexPairs, costRules);
            }
        }
    }
}
