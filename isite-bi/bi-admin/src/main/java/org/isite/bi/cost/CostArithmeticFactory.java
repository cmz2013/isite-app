package org.isite.bi.cost;

import org.isite.bi.data.enums.CostType;
import org.isite.bi.data.vo.CostSubject;
import org.isite.commons.cloud.factory.AbstractFactory;
import org.springframework.stereotype.Component;

/**
 * @Description 项目费用算法工厂类
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
public class CostArithmeticFactory extends AbstractFactory<CostArithmetic<? extends CostSubject, ?>, CostType, String> {
}
