package org.isite.exam.service;

import org.isite.exam.mapper.QuestionMapper;
import org.isite.exam.po.QuestionPo;
import org.isite.exam.data.enums.QuestionType;
import org.isite.mybatis.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Service
public class QuestionService extends ModelService<QuestionPo, Long> {

    @Autowired
    public QuestionService(QuestionMapper questionMapper) {
        super(questionMapper);
    }

    /**
     * 查询题目
     */
    public List<QuestionPo> findQuestions(int poolId, QuestionType questionType) {
        QuestionPo query = new QuestionPo();
        query.setPoolId(poolId);
        query.setQuestionType(questionType);
        return findList(query);
    }

    /**
     * 根据题库ID和题型，统计题目总数
     */
    public Integer count(Integer poolId, QuestionType questionType) {
        QuestionPo query = new QuestionPo();
        query.setPoolId(poolId);
        query.setQuestionType(questionType);
        return count(query);
    }
}
