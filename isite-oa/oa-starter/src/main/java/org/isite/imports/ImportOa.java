package org.isite.imports;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
@EnableFeignClients(basePackages = {"org.isite.oa.client"})
@ComponentScan(basePackages = {"org.isite.oa"})
public class ImportOa {
}
