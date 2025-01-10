package org.isite.oa.service;

import org.isite.mybatis.service.PoService;
import org.isite.oa.mapper.SalaryBenefitsMapper;
import org.isite.oa.po.SalaryBenefitsPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class SalaryBenefitsService extends PoService<SalaryBenefitsPo, Integer> {

    @Autowired
    public SalaryBenefitsService(SalaryBenefitsMapper mapper) {
        super(mapper);
    }
}
