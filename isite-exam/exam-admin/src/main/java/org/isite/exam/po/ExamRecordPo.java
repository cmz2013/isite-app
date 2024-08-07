package org.isite.exam.po;

import lombok.Getter;
import lombok.Setter;
import org.isite.mybatis.data.Po;

import javax.persistence.Table;
import java.util.Date;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Table(name = "exam_record")
public class ExamRecordPo extends Po<Long> {
    /**
     * 考试名称
     */
    private String title;
    /**
     * 考试场景ID
     */
    private Integer sceneId;
    /**
     * 试卷ID
     */
    private Integer paperId;
    /**
     * 总分
     */
    private Integer totalScore;
    /**
     * 考试时间（秒）,0不限制
     */
    private Integer examSecond;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户分数
     */
    private Integer userScore;
    /**
     * 提交时间
     */
    private Date submitTime;
    /**
     * 租户ID
     */
    private Integer tenantId;
}
