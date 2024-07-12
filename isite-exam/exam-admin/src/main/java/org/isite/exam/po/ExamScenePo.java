package org.isite.exam.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Model;
import org.isite.mybatis.type.EnumTypeHandler;
import org.isite.misc.data.enums.ObjectType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "exam_scene")
public class ExamScenePo extends Model<Integer> {
    /**
     * 考试名称
     */
    private String title;
    /**
     * 对象类型
     */
    @ColumnType(typeHandler = EnumTypeHandler.class)
    private ObjectType objectType;
    /**
     * 对象值
     */
    private String objectValue;
    /**
     * 试卷ID
     */
    private Integer paperId;
    /**
     * 是否支持需考
     */
    private Boolean continues;
}
