package org.isite.exam.data.vo;

import lombok.Getter;
import lombok.Setter;
import org.isite.commons.lang.data.Vo;

import java.util.List;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
public class ExamDetail extends Vo<Long> {
    /**
     * 试卷组成模块
     */
    private List<ExamModule> examModules;
    /**
     * 用户答案
     */
    private List<UserAnswer> userAnswers;
}
