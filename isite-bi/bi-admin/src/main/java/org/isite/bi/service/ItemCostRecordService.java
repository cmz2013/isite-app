package org.isite.bi.service;

import lombok.extern.slf4j.Slf4j;
import org.isite.bi.mapper.ItemCostRecordMapper;
import org.isite.bi.po.CostSubjectPo;
import org.isite.bi.po.ItemCostRecordPo;
import org.isite.mybatis.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @Description 单项费用记录Service
 * @Author <font color='blue'>zhangcm</font>
 */
@Slf4j
@Service
public class ItemCostRecordService extends ModelService<ItemCostRecordPo, Long> {

    public ItemCostRecordService(ItemCostRecordMapper mapper) {
        super(mapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addCostRecord(Collection<CostSubjectPo> subjectPos) {
        //TODO
    }
}
