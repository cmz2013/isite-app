package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.lang.data.Vo;
import org.isite.exam.data.enums.DifficultyLevel;
import org.isite.exam.data.enums.QuestionType;

/**
 * 题目
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class Question extends Vo<Long> {
    /**
     * 专业ID
     */
    private Integer majorId;
    /**
     * 题型
     */
    private QuestionType questionType;
    /**
     * 难度
     */
    private DifficultyLevel difficultyLevel;
    /**
     * 题干
     */
    private QuestionStem questionStem;
    /**
     * 答案解析
     */
    private String answerAnalysis;
    /**
     * 标签
     */
    private String tags;
    /**
     * 备注
     */
    private String remark;
    /**
     * 题库ID
     */
    private Integer poolId;
}
