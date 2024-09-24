package org.isite.exam.data.dto;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.cloud.data.dto.Dto;
import org.isite.commons.cloud.data.op.Add;
import org.isite.commons.cloud.data.op.Update;
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
     * 标签
     */
    private String tags;
    /**
     * 题库ID
     */
    @NotNull
    private Integer poolId;
    /**
     * 难度
     */
    @NotNull(groups = {Add.class, Update.class})
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
     * 备注
     */
    private String remark;

}
