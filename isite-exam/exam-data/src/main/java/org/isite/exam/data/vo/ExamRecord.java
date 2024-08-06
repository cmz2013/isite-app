package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.lang.data.Vo;

import java.util.Date;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class ExamRecord extends Vo<Long> {
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
     * 交卷时间
     */
    private Date submitTime;
    /**
     * 考试详情
     */
    private ExamDetail examDetail;
}
