package org.isite.exam.service;

import org.isite.exam.mapper.QuestionRuleMapper;
import org.isite.exam.po.QuestionRulePo;
import org.isite.mybatis.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Service
public class QuestionRuleService extends ModelService<QuestionRulePo, Integer> {

    @Autowired
    public QuestionRuleService(QuestionRuleMapper questionRuleMapper) {
        super(questionRuleMapper);
    }

    /**
     * 根据试卷ID查询随机选题规则
     */
    public List<QuestionRulePo> findByPaperId(Integer paperId) {
        QuestionRulePo query = new QuestionRulePo();
        query.setPaperId(paperId);
        return findList(query);
    }
}
