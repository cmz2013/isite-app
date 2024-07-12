package org.isite.exam.core;

import org.isite.commons.cloud.AbstractFactory;
import org.isite.exam.data.enums.ScoreAlgorithmType;
import org.springframework.stereotype.Component;

/**
 * 考试算分接口工厂类
 * @author <font color='blue'>zhangcm</font>
 */
@Component
public class ScoreCalculatorFactory extends AbstractFactory<ScoreCalculator, ScoreAlgorithmType, Integer> {
}
