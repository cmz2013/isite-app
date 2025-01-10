package org.isite.oa.service;

import org.isite.mybatis.service.PoService;
import org.isite.oa.mapper.BankAccountMapper;
import org.isite.oa.po.BankAccountPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class BankAccountService extends PoService<BankAccountPo, Integer> {

    @Autowired
    public BankAccountService(BankAccountMapper mapper) {
        super(mapper);
    }
}
