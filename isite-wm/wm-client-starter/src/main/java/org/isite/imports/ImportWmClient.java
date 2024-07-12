package org.isite.imports;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author <font color='blue'>zhangcm</font>
 */
@Component
@EnableFeignClients(basePackages = {"org.isite.wm.client"})
@ComponentScan(basePackages = {"org.isite.wm"})
public class ImportWmClient {
}
