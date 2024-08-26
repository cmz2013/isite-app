package org.isite.bi.service;

import lombok.extern.slf4j.Slf4j;
import org.isite.bi.mapper.ItemCostMapper;
import org.isite.bi.po.ItemCostPo;
import org.isite.mybatis.service.PoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @Description 单项费用记录Service
 * @Author <font color='blue'>zhangcm</font>
 */
@Slf4j
@Service
public class ItemCostService extends PoService<ItemCostPo, Long> {

    public ItemCostService(ItemCostMapper mapper) {
        super(mapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addCostRecord(Collection<ItemCostPo> costRecordPos) {
        //TODO
    }
}
