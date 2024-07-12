package org.isite.exam.core;

import org.isite.commons.cloud.AbstractFactory;
import org.isite.exam.data.enums.QuestionMode;
import org.springframework.stereotype.Component;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Component
public class ExamPaperAccessorFactory extends AbstractFactory<ExamPaperAccessor, QuestionMode, Integer> {
}
