package org.isite.oa.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.List;
/**
 * @Description 工资税配置
 * @Author <font color='blue'>zhangcm</font>
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "salary-tax")
public class SalaryTaxConfig implements InitializingBean {
    //免税额度（分）
    private long taxFree;
    //税率
    private List<TaxRate> taxRates;
    //最大税率（%）
    private int maxRate;

    public int getTaxRate(long taxableIncome) {
        if (CollectionUtils.isNotEmpty(taxRates)) {
            for (TaxRate taxRate : taxRates) {
                if (taxableIncome <= taxRate.getThreshold()) {
                    return taxRate.getRate();
                }
            }
        }
        return this.maxRate;
    }

    @Override
    public void afterPropertiesSet() {
        if (CollectionUtils.isNotEmpty(taxRates)) {
            taxRates.sort(Comparator.comparingLong(TaxRate::getThreshold));
        }
    }

    @Getter
    @Setter
    public static class TaxRate {
        //起征点（分）。不含免税额度
        private long threshold;
        //税率（%）
        private int rate;
    }
}
