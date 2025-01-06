package org.isite.exam.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.enums.ScoreAlgorithm;
import org.isite.mybatis.data.Po;
import org.isite.mybatis.type.EnumTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;

/**
 * @Description 评分规则。创建试卷的时候必须按题型设置评分规则
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "score_rule")
public class ScoreRulePo extends Po<Integer> {
    /**
     * 试卷ID
     */
    private Integer paperId;
    /**
     * 题型
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private QuestionType questionType;
    /**
     * 题目总分
     */
    private Integer score;
    /**
     * 考试分数算法类型
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private ScoreAlgorithm scoreAlgorithm;
}
