package org.isite.exam.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.exam.data.enums.QuestionType;
import org.isite.mybatis.data.Model;
import org.isite.mybatis.type.EnumTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;

/**
 * @Description 考卷题目，用于手动组卷
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "exam_question")
public class ExamQuestionPo extends Model<Integer> {
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
     * 题目ID
     */
    private Integer questionId;
}
