package org.isite.exam.service;

import org.isite.exam.mapper.ExamPoolMapper;
import org.isite.exam.po.ExamPoolPo;
import org.isite.mybatis.service.PoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class ExamPoolService extends PoService<ExamPoolPo, Integer> {

    @Autowired
    public ExamPoolService(ExamPoolMapper examPoolMapper) {
        super(examPoolMapper);
    }
}
