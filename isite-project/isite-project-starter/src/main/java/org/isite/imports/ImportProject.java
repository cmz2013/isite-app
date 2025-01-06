package org.isite.imports;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author <font color='blue'>zhangcm</font>
 */
@Component
//@EnableFeignClients(basePackages = {"org.isite.project.client"})
@ComponentScan(basePackages = {"org.isite.project"})
public class ImportProject {
}
