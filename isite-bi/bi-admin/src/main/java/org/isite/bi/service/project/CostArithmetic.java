package org.isite.bi.service.project;

import org.isite.bi.data.enums.project.CostType;
import org.isite.bi.data.vo.project.CostElement;
import org.isite.bi.data.vo.project.CostRule;
import org.isite.bi.data.vo.project.CostSubject;
import org.isite.commons.cloud.factory.Strategy;
import org.isite.mybatis.data.Po;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.isite.commons.lang.data.Constants.ONE;
import static org.isite.commons.lang.utils.TreeUtils.get;
import static org.isite.commons.lang.utils.TypeUtils.cast;

/**
 * @Description 费用算法接口
 * @param <S> 费用科目
 * @param <C> 费用汇总数据记录
 * @Author <font color='blue'>zhangcm</font>
 */
public abstract class CostArithmetic<S extends CostSubject, C extends Po<?>> implements Strategy<CostType> {
    /**
     * @Description 查询费用科目。项目中的多个条目（item）以及每个条目中的多个阶段（stage）
     */
    public abstract List<S> findCostSubject(CostElement costElement);
    /**
     * 汇总费用
     */
    public void sumCostSubjects(List<S> costSubjects) {
        C costRecord = null;
        for (S costSubject : costSubjects) {
            costRecord = sumLeafNode(costSubject, costRecord);
            if (null == costRecord) {
                return;
            }
        }
        saveCostRecord(singletonList(costRecord));
    }

    /**
     * 从规则树叶子节点到根节点逐层汇总费用
     */
    public void sumCostIndexPairs(List<CostIndexPair> costIndexPairs, List<CostRule> costRules) {
        //key: 规则ID, value: 费用数据
        Map<Integer, C> ruleCostRecord = new HashMap<>();
        //key: 层级, value: 层节点Map
        Map<Integer, List<CostRecordPair<C>>> layerCostRecordPair = new HashMap<>();
        int level = ONE;

        for (CostIndexPair costIndexPair : costIndexPairs) {
            //汇总费用科目到规则树的叶子节点
            C cost = sumLeafNode(cast(costIndexPair.getCostSubject()),
                    ruleCostRecord.get(costIndexPair.getCostRule().getId()), costIndexPair.getCostRule());

            if (null == cost) {
                return;
            }
            ruleCostRecord.put(costIndexPair.getCostRule().getId(), cost);
            //封装叶子层的汇总数据
            initLayerNode(costIndexPair.getLevel(), costIndexPair.getCostRule(), ruleCostRecord, layerCostRecordPair);
            if (level < costIndexPair.getLevel()) {
                level = costIndexPair.getLevel();
            }
        }

        boolean stopSummarize = false;
        //向根节点逐层汇总数据
        while (level > ONE) {
            int pLevel = level - ONE;
            for (CostRecordPair<C> costRecordPair : layerCostRecordPair.get(level)) {
                Integer rulePid = costRecordPair.getCostRule().getPid();
                CostRule pRule = get(rulePid, costRules);
                C cost = sumBranchNode(costRecordPair.getCostRecord(), ruleCostRecord.get(rulePid), pRule);
                if (null == cost) {
                    if (!stopSummarize) {
                        stopSummarize = true;
                    }
                } else {
                    ruleCostRecord.put(rulePid, cost);
                }
                //设置下层的汇总数据
                initLayerNode(pLevel, pRule, ruleCostRecord, layerCostRecordPair);
            }
            if (stopSummarize) {
                break;
            }
            level--;
        }
        saveCostRecord(ruleCostRecord.values());
    }

    /**
     * 根据科目规则树，封装每层节点的汇总数据
     * @param level 层级
     * @param rule 科目规则树
     * @param layerCostRecordPair 层汇总数据
     */
    private void initLayerNode(int level, CostRule rule, Map<Integer, C> ruleCostRecord,
                               Map<Integer, List<CostRecordPair<C>>> layerCostRecordPair) {

        if (ONE == level) {
            return;
        }
        if (isNotEmpty(layerCostRecordPair.computeIfAbsent(level, key -> new LinkedList<>()))) {
            for (CostRecordPair<C> costRecordPair : layerCostRecordPair.get(level)) {
                if (rule.getId().equals(costRecordPair.getCostRule().getId())) {
                    costRecordPair.setCostRecord(ruleCostRecord.get(rule.getId()));
                    return;
                }
            }
        }
        layerCostRecordPair.get(level).add(new CostRecordPair<>(rule, ruleCostRecord.get(rule.getId())));
    }

    /**
     * 根据汇总数据计算费用指标，保存费用记录
     */
    public abstract void saveCostRecord(Collection<C> costRecord);

    /**
     * @Description 规则树叶子节点费用汇总。如果返回null，则终止费用汇总和指标计算
     * @param costSubject 费用科目
     * @param costRecord 费用归总数据
     * @param costRule 费用科目规则
     * @return 费用汇总数据
     */
    protected abstract C sumLeafNode(S costSubject, C costRecord, CostRule costRule);

    /**
     * @Description 费用科目汇总。如果返回null，则终止费用汇总和指标计算
     * @param costSubject 费用科目
     * @param costRecord 费用汇总数据
     * @return 费用汇总数据
     */
    protected abstract C sumLeafNode(S costSubject, C costRecord);

    /**
     * @Description 规则树分支节点费用汇总。如果返回null，则中断当前层向上层汇总费用数据，然后计算指标。
     * @param sCostRecord 子节点费用归总数据
     * @param pCostRecord 父节点费用归总数据
     * @param pCostRule 父节点费用科目规则
     * @return sCostRecord和pCostRecord的汇总数据
     */
    protected abstract C sumBranchNode(C sCostRecord, C pCostRecord, CostRule pCostRule);
}
