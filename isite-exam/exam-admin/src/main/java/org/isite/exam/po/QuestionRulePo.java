package org.isite.exam.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.exam.data.enums.QuestionType;
import org.isite.mybatis.data.Model;
import org.isite.mybatis.type.EnumTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;

/**
 * @Description 随机选题规则
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "question_rule")
public class QuestionRulePo extends Model<Integer> {
    /**
     * 试卷ID
     */
    private Integer paperId;
    /**
     * 题库ID
     */
    private Integer poolId;
    /**
     * 题型
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private QuestionType questionType;
    /**
     * 选题个数
     */
    private Integer number;
}
