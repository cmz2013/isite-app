package org.isite.exam.service;

import org.isite.exam.mapper.ExamSceneMapper;
import org.isite.exam.po.ExamScenePo;
import org.isite.mybatis.service.PoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class ExamSceneService extends PoService<ExamScenePo, Integer> {

    @Autowired
    public ExamSceneService(ExamSceneMapper examSceneMapper) {
        super(examSceneMapper);
    }
}
