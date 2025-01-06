package org.isite.exam.service;

import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.mapper.QuestionMapper;
import org.isite.exam.po.QuestionPo;
import org.isite.mybatis.service.PoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Service
public class QuestionService extends PoService<QuestionPo, Long> {

    @Autowired
    public QuestionService(QuestionMapper questionMapper) {
        super(questionMapper);
    }

    /**
     * 根据题库ID和题型，统计题目总数
     */
    public int count(Integer poolId, QuestionType questionType) {
        QuestionPo query = new QuestionPo();
        query.setPoolId(poolId);
        query.setQuestionType(questionType);
        return this.count(query);
    }
}
