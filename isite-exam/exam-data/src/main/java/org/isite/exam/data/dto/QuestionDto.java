package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.web.data.Dto;
import org.isite.commons.web.data.op.Add;
import org.isite.commons.web.data.op.Update;
import org.isite.exam.data.enums.DifficultyLevel;
import org.isite.exam.data.enums.QuestionType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description 题目
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class QuestionDto extends Dto<Long> {
    /**
     * 专业ID
     */
    @NotNull(groups = {Add.class, Update.class})
    private Integer majorId;
    /**
     * 题型
     */
    @NotNull(groups = {Add.class, Update.class})
    private QuestionType questionType;
    /**
     * 难度
     */
    private DifficultyLevel difficultyLevel;
    /**
     * 题干
     */
    @Valid
    @NotNull(groups = {Add.class, Update.class})
    private QuestionStemDto questionStems;
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
    @NotNull
    private Integer poolId;
}
