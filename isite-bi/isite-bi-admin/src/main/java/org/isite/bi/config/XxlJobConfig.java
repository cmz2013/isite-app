package org.isite.bi.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.isite.commons.cloud.utils.PropertyUtils.getApplicationName;
import static org.isite.commons.cloud.utils.PropertyUtils.getPort;

/**
 * @Description XXL-JOB执行器组件配置
 * @Author <font color='blue'>zhangcm</font>
 */
@Configuration
public class XxlJobConfig {

    // 集群多个地址，逗号隔开
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAppname(getApplicationName());
        xxlJobSpringExecutor.setAdminAddresses(adminAddresses);
        xxlJobSpringExecutor.setPort(getPort());
        xxlJobSpringExecutor.setAccessToken(accessToken);
        return xxlJobSpringExecutor;
    }
}
