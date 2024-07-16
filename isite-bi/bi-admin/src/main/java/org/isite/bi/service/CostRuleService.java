package org.isite.bi.service;

import lombok.extern.slf4j.Slf4j;
import org.isite.bi.cost.CostRulePair;
import org.isite.bi.mapper.CostRuleMapper;
import org.isite.bi.po.CostRulePo;
import org.isite.bi.data.enums.CostType;
import org.isite.bi.data.vo.CostRule;
import org.isite.bi.data.vo.CostSubject;
import org.isite.mybatis.service.TreeModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.isite.commons.lang.data.Constants.ONE;

/**
 * @Description 费用科目树规则Service
 * @Author <font color='blue'>zhangcm</font>
 */
@Slf4j
@Service
public class CostRuleService extends TreeModelService<CostRulePo, Integer> {
    /**
     * JS脚本引擎
     */
    private ScriptEngine scriptEngine;

    @Autowired
    protected CostRuleService(CostRuleMapper costRuleMapper) {
        super(costRuleMapper);
    }

    @PostConstruct
    public void initScriptEngine() throws ScriptException {
        scriptEngine = new ScriptEngineManager().getEngineByName("javascript");
        scriptEngine.eval(new InputStreamReader(
                requireNonNull(this.getClass().getResourceAsStream("/js/ProjectCost.js"))));
    }

    public List<CostRulePo> findCostRules(CostType costType) {
        CostRulePo query = new CostRulePo();
        query.setCostType(costType);
        return findList(query);
    }

    /**
     * 匹配规则，过滤掉不需要参与计算的科目
     */
    public List<CostRulePair> matches(List<? extends CostSubject> subjects, List<CostRule> rules) {
        List<CostRulePair> costRulePairs = new ArrayList<>();
        subjects.forEach(subject -> {
            //从规则树根节点到叶子节点逐层匹配
            CostRulePair costRulePair = matches(ONE, subject, rules);
            if (null != costRulePair) {
                costRulePairs.add(costRulePair);
            }
        });
        return costRulePairs;
    }

    /**
     * 匹配规则树列表
     */
    private CostRulePair matches(Integer level, CostSubject subject, List<CostRule> rules) {
        for (CostRule rule : rules) {
            CostRulePair costRulePair = matches(level, subject, rule);
            if (null != costRulePair) {
                return costRulePair;
            }
        }
        return null;
    }

    /**
     * 从规则树父节点到叶子节点逐层匹配
     */
    private CostRulePair matches(Integer level, CostSubject subject, CostRule rule) {
        if (isNotBlank(rule.getExpressions()) && !parseExpression(subject, rule.getExpressions())) {
            return null;
        }
        if (null != rule.getChildren() && !rule.getChildren().isEmpty()) {
            return matches(++level, subject, rule.getChildren());
        }
        return new CostRulePair(level, subject, rule);
    }

    /**
     * 使用 subject 解析规则表达式 expression
     */
    private boolean parseExpression(CostSubject subject, String expression) {
        try {
            scriptEngine.put("subject", subject);
            return (Boolean) scriptEngine.eval(expression);
        } catch (ScriptException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
