package org.isite.exam.service;

import org.isite.exam.mapper.ExamQuestionMapper;
import org.isite.exam.po.ExamQuestionPo;
import org.isite.mybatis.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Service
public class ExamQuestionService extends ModelService<ExamQuestionPo, Integer> {

    @Autowired
    public ExamQuestionService(ExamQuestionMapper examQuestionMapper) {
        super(examQuestionMapper);
    }
}
