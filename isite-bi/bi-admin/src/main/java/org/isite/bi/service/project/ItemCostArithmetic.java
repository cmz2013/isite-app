package org.isite.bi.service.project;

import org.isite.bi.data.enums.project.CostType;
import org.isite.bi.data.vo.project.CostElement;
import org.isite.bi.data.vo.project.CostRule;
import org.isite.bi.data.vo.project.ItemSubject;
import org.isite.bi.po.project.ItemCostPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.isite.bi.data.enums.project.CostType.ITEM;

/**
 * @Description 单项费用算法
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class ItemCostArithmetic extends CostArithmetic<ItemSubject, ItemCostPo> {

    private ItemCostService costRecordService;

    @Autowired
    public void setCostRecordService(ItemCostService costRecordService) {
        this.costRecordService = costRecordService;
    }

    /**
     * 查询项目的单项费用科目
     */
    @Override
    public List<ItemSubject> findCostSubject(CostElement costElement) {
        return null;
    }

    @Override
    public void saveCostRecord(Collection<ItemCostPo> costRecordPos) {
        costRecordService.addCostRecord(costRecordPos);
    }

    @Override
    protected ItemCostPo sumLeafNode(ItemSubject subject, ItemCostPo costData, CostRule rule) {
        return null;
    }

    @Override
    protected ItemCostPo sumLeafNode(ItemSubject subject, ItemCostPo cost) {
        return null;
    }

    @Override
    protected ItemCostPo sumBranchNode(ItemCostPo sCost, ItemCostPo pCost, CostRule pRule) {
        return null;
    }

    @Override
    public CostType[] getIdentities() {
        return new CostType[] {ITEM};
    }
}
