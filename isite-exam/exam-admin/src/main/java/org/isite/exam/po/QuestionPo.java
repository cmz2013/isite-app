package org.isite.exam.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.exam.data.enums.DifficultyLevel;
import org.isite.exam.data.enums.QuestionType;
import org.isite.mybatis.data.Model;
import org.isite.mybatis.type.EnumTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;

/**
 * @Description 题目
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "question")
public class QuestionPo extends Model<Long> {
    /**
     * 专业ID
     */
    private Integer majorId;
    /**
     * 题型
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private QuestionType questionType;
    /**
     * 难度
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private DifficultyLevel difficultyLevel;
    /**
     * 题干JSON
     */
    private String questionStem;
    /**
     * 客观题选项（VO）JSON
     */
    private String options;
    /**
     * 客观题正确答案
     */
    private String rightAnswer;
    /**
     * 答案解析
     */
    private String answerAnalysis;
    /**
     * 标签
     */
    private String tags;
    /**
     * 题库ID
     */
    private Integer poolId;
    /**
     * 备注
     */
    private String remark;
}
