package org.isite.exam.service;

import org.isite.exam.mapper.ScoreRuleMapper;
import org.isite.exam.po.ScoreRulePo;
import org.isite.mybatis.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Service
public class ScoreRuleService extends ModelService<ScoreRulePo, Integer> {

    @Autowired
    public ScoreRuleService(ScoreRuleMapper scoreRuleMapper) {
        super(scoreRuleMapper);
    }

    /**
     * 根据试卷ID查询评分规则
     */
    public List<ScoreRulePo> findByPaperId(Integer paperId) {
        ScoreRulePo query = new ScoreRulePo();
        query.setPaperId(paperId);
        return findList(query);
    }
}
