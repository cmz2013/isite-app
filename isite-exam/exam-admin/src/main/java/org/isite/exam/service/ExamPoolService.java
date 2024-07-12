package org.isite.exam.service;

import org.isite.exam.mapper.ExamPoolMapper;
import org.isite.exam.po.ExamPoolPo;
import org.isite.mybatis.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Service
public class ExamPoolService extends ModelService<ExamPoolPo, Integer> {

    @Autowired
    public ExamPoolService(ExamPoolMapper examPoolMapper) {
        super(examPoolMapper);
    }
}
