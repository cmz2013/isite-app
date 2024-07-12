package org.isite.exam.service;

import org.isite.exam.mapper.ExamSceneMapper;
import org.isite.exam.po.ExamScenePo;
import org.isite.mybatis.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Service
public class ExamSceneService extends ModelService<ExamScenePo, Integer> {

    @Autowired
    public ExamSceneService(ExamSceneMapper examSceneMapper) {
        super(examSceneMapper);
    }
}
