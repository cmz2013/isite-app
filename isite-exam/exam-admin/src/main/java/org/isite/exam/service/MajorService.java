package org.isite.exam.service;

import org.isite.exam.mapper.MajorMapper;
import org.isite.exam.po.MajorPo;
import org.isite.mybatis.service.TreePoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Service
public class MajorService extends TreePoService<MajorPo, Integer> {

    @Autowired
    public MajorService(MajorMapper majorMapper) {
        super(majorMapper);
    }
}
