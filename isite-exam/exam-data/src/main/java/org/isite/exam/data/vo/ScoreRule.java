package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.Setter;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.enums.ScoreAlgorithmType;
import org.isite.commons.lang.data.Vo;

/**
 * 评分规则。创建试卷的时候必须按题型设置评分规则
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class ScoreRule extends Vo<Integer> {
    /**
     * 试卷ID
     */
    private Integer paperId;
    /**
     * 题型
     */
    private QuestionType questionType;
    /**
     * 每道题分数/题目总分，配合分数算法使用
     */
    private Integer score;
    /**
     * 考试分数算法类型
     */
    private ScoreAlgorithmType scoreAlgorithmType;
}
