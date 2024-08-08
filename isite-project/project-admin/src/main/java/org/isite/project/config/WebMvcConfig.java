package org.isite.project.config;

import org.isite.commons.web.config.WebMvcAdapter;
import org.isite.imports.EnableWeb;
import org.springframework.context.annotation.Configuration;

/**
 * @Description data-starter会自动扫描和注册commons-web组件
 * @Author <font color='blue'>zhangcm</font>
 */
@EnableWeb
@Configuration
public class WebMvcConfig extends WebMvcAdapter {
}