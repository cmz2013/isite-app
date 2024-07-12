package org.isite.bi.cost;

import org.isite.bi.po.CostSubjectPo;
import org.isite.bi.po.ProjectCostPo;
import org.isite.bi.service.ItemCostRecordService;
import org.isite.bi.data.vo.CostRule;
import org.isite.bi.data.vo.ItemCostSubject;
import org.isite.commons.lang.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.isite.bi.data.constants.BiConstants.COST_TYPE_ITEM;

/**
 * @Description 单项费用算法
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
@Key(values = COST_TYPE_ITEM)
public class ItemCostArithmetic extends CostArithmetic<ItemCostSubject, CostSubjectPo> {

    private ItemCostRecordService costRecordService;

    @Autowired
    public void setCostRecordService(ItemCostRecordService costRecordService) {
        this.costRecordService = costRecordService;
    }

    @Override
    public List<ItemCostSubject> findCostSubject(ProjectCostPo costPo) {
        // TODO
        return null;
    }

    @Override
    public void runProjectCost(Collection<CostSubjectPo> subjectPos) {
        costRecordService.addCostRecord(subjectPos);
    }

    @Override
    protected CostSubjectPo sumLeafNode(ItemCostSubject subject, CostSubjectPo cost, CostRule rule) {
        return null;
    }

    @Override
    protected CostSubjectPo sumBranchNode(CostSubjectPo sCost, CostSubjectPo pCost, CostRule pRule) {
        return null;
    }
}
