package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.lang.data.Vo;
import org.isite.exam.data.enums.QuestionType;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Setter
@Getter
public class ExamQuestion extends Vo<Integer> {
    /**
     * 试卷ID
     */
    private Integer paperId;
    /**
     * 题型
     */
    private QuestionType questionType;
    /**
     * 题目ID
     */
    private Integer questionId;
}
