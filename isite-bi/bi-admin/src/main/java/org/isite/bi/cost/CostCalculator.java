package org.isite.bi.cost;

import org.isite.bi.po.CostRulePo;
import org.isite.bi.po.ProjectCostPo;
import org.isite.bi.service.CostRuleService;
import org.isite.bi.data.enums.CostType;
import org.isite.bi.data.vo.CostRule;
import org.isite.bi.data.vo.CostSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.isite.commons.cloud.data.Converter.convert;
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

    public void execute(ProjectCostPo costPo, CostType costType) {
        CostArithmetic<? extends CostSubject, ?> costArithmetic = costArithmeticFactory.get(costType);
        List<? extends CostSubject> subjects = costArithmetic.findCostSubject(costPo);
        if (isEmpty(subjects)) {
            return;
        }
        List<CostRulePo> rulePos = costRuleService.findCostRules(costType);
        if (isEmpty(rulePos)) {
            costArithmetic.sumCostSubject(subjects);
        } else {
            List<CostRule> rules = toTree(rulePos, po -> convert(po, CostRule::new));
            List<CostPair> costPairs = costRuleService.matches(subjects, rules);
            if(isNotEmpty(costPairs)) {
                costArithmetic.sumCostPair(costPairs, rules);
            }
        }
    }
}
