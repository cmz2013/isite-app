package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.enums.ScoreAlgorithmType;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 试卷组成模块
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@NoArgsConstructor
public class ExamModule implements Serializable {
    /**
     * 题目分数/题目总分，配合考试分数算法使用
     */
    private Integer score;
    /**
     * 考试分数算法类型
     */
    private ScoreAlgorithmType scoreAlgorithmType;
    /**
     * 题型
     */
    private QuestionType questionType;
    /**
     * 题目
     */
    private List<Question> questions;

    /**
     * 试卷组成模块。创建试卷的时候必须按题型设置评分规则
     * @param scoreRule 按题型设置评分规则
     * @param questions 题目
     */
    public ExamModule(ScoreRule scoreRule, List<Question> questions) {
        this.questionType = scoreRule.getQuestionType();
        this.score = scoreRule.getScore();
        this.scoreAlgorithmType = scoreRule.getScoreAlgorithmType();
        this.questions = questions;
    }
}
