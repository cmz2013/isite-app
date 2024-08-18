package org.isite.bi.cost;

import org.isite.bi.data.enums.CostType;
import org.isite.bi.data.vo.CostRule;
import org.isite.bi.data.vo.ItemCostSubject;
import org.isite.bi.po.ItemCostRecordPo;
import org.isite.bi.po.ProjectCostPo;
import org.isite.bi.service.ItemCostRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.isite.bi.data.enums.CostType.ITEM;

/**
 * @Description 单项费用算法
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class ItemCostArithmetic extends CostArithmetic<ItemCostSubject, ItemCostRecordPo> {

    private ItemCostRecordService costRecordService;

    @Autowired
    public void setCostRecordService(ItemCostRecordService costRecordService) {
        this.costRecordService = costRecordService;
    }

    /**
     * 查询项目的单项费用科目
     */
    @Override
    public List<ItemCostSubject> findCostSubject(ProjectCostPo costPo) {
        return null;
    }

    @Override
    public void saveCostRecord(Collection<ItemCostRecordPo> costRecordPos) {
        costRecordService.addCostRecord(costRecordPos);
    }

    @Override
    protected ItemCostRecordPo sumLeafNode(ItemCostSubject subject, ItemCostRecordPo costData, CostRule rule) {
        return null;
    }

    @Override
    protected ItemCostRecordPo sumLeafNode(ItemCostSubject subject, ItemCostRecordPo cost) {
        return null;
    }

    @Override
    protected ItemCostRecordPo sumBranchNode(ItemCostRecordPo sCost, ItemCostRecordPo pCost, CostRule pRule) {
        return null;
    }

    @Override
    public CostType[] getIdentities() {
        return new CostType[] {ITEM};
    }
}
