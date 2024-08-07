package org.isite.exam.core;

import org.isite.commons.cloud.AbstractFactory;
import org.isite.exam.data.enums.ScoreAlgorithm;
import org.springframework.stereotype.Component;

/**
 * @Description 考试算分接口工厂类
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class ScoreCalculatorFactory extends AbstractFactory<ScoreCalculator, ScoreAlgorithm, Integer> {
}
