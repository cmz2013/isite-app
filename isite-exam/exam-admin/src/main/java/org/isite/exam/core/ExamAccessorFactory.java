package org.isite.exam.core;

import org.isite.commons.cloud.factory.AbstractFactory;
import org.isite.exam.data.enums.QuestionMode;
import org.springframework.stereotype.Component;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class ExamAccessorFactory extends AbstractFactory<ExamAccessor, QuestionMode, Integer> {
}
