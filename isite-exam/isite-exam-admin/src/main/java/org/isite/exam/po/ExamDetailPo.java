package org.isite.exam.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;

import javax.persistence.Table;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "exam_detail")
public class ExamDetailPo extends Po<Long> {
    /**
     * 考试记录id
     */
    private Long examRecordId;
    /**
     * 试卷组成模块JSON
     */
    private String examModules;
    /**
     * 用户答案JSON
     */
    private String userAnswers;
}
