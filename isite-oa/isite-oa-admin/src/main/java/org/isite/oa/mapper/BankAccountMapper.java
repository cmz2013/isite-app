package org.isite.oa.mapper;

import org.isite.mybatis.mapper.PoMapper;
import org.isite.oa.po.BankAccountPo;
import org.springframework.stereotype.Repository;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Repository
public interface BankAccountMapper extends PoMapper<BankAccountPo, Integer> {
}
