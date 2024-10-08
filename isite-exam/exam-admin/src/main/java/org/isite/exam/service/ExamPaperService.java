package org.isite.exam.service;

import org.isite.exam.mapper.ExamPaperMapper;
import org.isite.exam.po.ExamPaperPo;
import org.isite.mybatis.service.PoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class ExamPaperService extends PoService<ExamPaperPo, Integer> {

    @Autowired
    public ExamPaperService(ExamPaperMapper examPaperMapper) {
        super(examPaperMapper);
    }
}
