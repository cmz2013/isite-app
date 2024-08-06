package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.lang.data.Vo;
import org.isite.exam.data.enums.QuestionType;

/**
 * @Description 随机选题规则
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class QuestionRule extends Vo<Integer> {
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
    private QuestionType questionType;
    /**
     * 选题个数
     */
    private Integer number;
}
