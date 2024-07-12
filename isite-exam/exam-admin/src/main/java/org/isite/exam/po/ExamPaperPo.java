package org.isite.exam.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.exam.data.enums.QuestionMode;
import org.isite.mybatis.data.Model;
import org.isite.mybatis.type.EnumTypeHandler;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "exam_paper")
public class ExamPaperPo extends Model<Integer> {
    /**
     * 试卷名称
     */
    private String title;
    /**
     * 试卷总分
     */
    private Integer totalScore;
    /**
     * 考试时间（秒）,0不限制
     */
    private Integer examSecond;
    /**
     * 题目数量
     */
    private Integer questionNumber;
    /**
     * 选题方式
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private QuestionMode questionMode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 租户ID
     */
    private Integer tenantId;
}
