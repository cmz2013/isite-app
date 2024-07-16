package org.isite.bi.cost;

import org.isite.bi.po.CostRulePo;
import org.isite.bi.po.ProjectCostPo;
import org.isite.bi.data.vo.CostRule;
import org.isite.bi.data.vo.CostSubject;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.isite.commons.lang.data.Constants.ONE;
import static org.isite.commons.lang.data.TreeUtils.get;

/**
 * @Description 费用算法接口
 * @param <S> 费用科目
 * @param <C> 费用汇总数据
 * @Author <font color='blue'>zhangcm</font>
 */
public abstract class CostArithmetic<S extends CostSubject, C> {
    private static final String LAYER_NODE_RULE = "rule";
    private static final String LAYER_NODE_COST = "cost";

    /**
     * 查询用于计算费用的参数
     */
    public abstract List<S> findCostSubject(ProjectCostPo costPo);

    /**
     * 先根据规则树逐层汇总费用，再计算费用指标
     */
    public void sumCostSubject(List<? extends CostSubject> subjects) {
        C cost = null;
        List<C> costs = new LinkedList<>();
        for (CostSubject subject : subjects) {
            cost = sumLeafNode((S) subject, cost, null);
            if (null == cost) {
                return;
            }
            costs.add(cost);
        }
        saveProjectCost(costs);
    }

    /**
     * 先从规则树叶子节点到根节点逐层汇总费用，再计算费用指标
     */
    public void sumCostPair(List<CostRulePair> costRulePairs, List<CostRule> rules) {
        //key: 规则ID, value: 费用数据
        Map<Integer, C> costMap = new HashMap<>();
        //key: 层级, value: 层节点Map
        Map<Integer, List<Map<String, Object>>> layerMap = new HashMap<>();
        int level = ONE;

        for (CostRulePair costRulePair : costRulePairs) {
            //汇总费用科目到规则树的叶子节点
            C cost = sumLeafNode((S) costRulePair.getSubject(), costMap.get(costRulePair.getRule().getId()), costRulePair.getRule());
            if (null == cost) {
                return;
            }
            costMap.put(costRulePair.getRule().getId(), cost);
            //封装叶子层的汇总数据
            initLayerNode(costRulePair.getLevel(), costRulePair.getRule(), costMap, layerMap);
            if (level < costRulePair.getLevel()) {
                level = costRulePair.getLevel();
            }
        }

        boolean stopSummarize = false;
        //向根节点逐层汇总数据
        while (level > ONE) {
            int pLevel = level - ONE;
            for (Map<String, Object> layerNode : layerMap.get(level)) {
                Integer rulePid = ((CostRule) layerNode.get(LAYER_NODE_RULE)).getPid();
                CostRule pRule = get(rules, rulePid);
                C cost = sumBranchNode((C) layerNode.get(LAYER_NODE_COST), costMap.get(rulePid), pRule);
                if (null == cost) {
                    if (!stopSummarize) {
                        stopSummarize = true;
                    }
                } else {
                    costMap.put(rulePid, cost);
                }
                //设置下层的汇总数据
                initLayerNode(pLevel, pRule, costMap, layerMap);
            }
            if (stopSummarize) {
                break;
            }
            level--;
        }
        saveProjectCost(costMap.values());
    }

    /**
     * 根据科目规则树，封装每层节点的汇总数据
     * @param level 层级
     * @param rule 科目规则树
     * @param layerMap 层汇总数据
     */
    private void initLayerNode(int level, CostRule rule,
            Map<Integer, C> costMap, Map<Integer, List<Map<String, Object>>> layerMap) {

        if (ONE == level) {
            return;
        }
        if (isNotEmpty(layerMap.computeIfAbsent(level, key -> new LinkedList<>()))) {
            for (Map<String, Object> layerNode : layerMap.get(level)) {
                if (rule.getId().equals(((CostRulePo) layerNode.get(LAYER_NODE_RULE)).getId())) {
                    layerNode.put(LAYER_NODE_COST, costMap.get(rule.getId()));
                    return;
                }
            }
        }
        Map<String, Object> layerNode = new HashMap<>();
        layerNode.put(LAYER_NODE_RULE, rule);
        layerNode.put(LAYER_NODE_COST, costMap.get(rule.getId()));
        layerMap.get(level).add(layerNode);
    }

    /**
     * 根据汇总数据计算费用指标，保存费用记录
     */
    public abstract void saveProjectCost(Collection<C> values);

    /**
     * 规则树叶子节点费用汇总。如果返回null，则终止费用汇总和指标计算。
     * @param subject 费用科目
     * @param cost 费用归总数据
     * @param rule 费用科目规则
     * @return subject和cost的汇总数据
     */
    protected abstract C sumLeafNode(S subject, C cost, CostRule rule);

    /**
     * 规则树分支节点费用汇总。如果返回null，则中断当前层向上层汇总费用数据，然后计算指标。
     * @param sCost 子节点费用归总数据
     * @param pCost 父节点费用归总数据
     * @param pRule 父节点费用科目规则
     * @return sCost和pCost的汇总数据
     */
    protected abstract C sumBranchNode(C sCost, C pCost, CostRule pRule);
}
