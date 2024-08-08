package org.isite.imports;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * 使用配置类扫描包路径
 * jar包的feign组件，仅仅依靠@ComponentScan是不够的，还须要@EnableFeignClients(basePackages = {})
 *
 * @author <font color='blue'>zhangcm</font>
 */
@Component
@EnableFeignClients(basePackages = {"org.isite.exam.client"})
@ComponentScan(basePackages = {"org.isite.exam.client"})
public class ImportExam {
}
