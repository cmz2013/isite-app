package org.isite.exam.core;

import org.isite.commons.cloud.AbstractFactory;
import org.isite.exam.data.enums.QuestionType;
import org.isite.exam.data.vo.Question;
import org.springframework.stereotype.Component;

/**
 * @Description 题目答案糊匹配接口工厂类
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class FuzzyMatcherFactory extends AbstractFactory<FuzzyMatcher<Question>, QuestionType, Integer> {
}
