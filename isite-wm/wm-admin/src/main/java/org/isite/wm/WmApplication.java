package org.isite.wm;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import static org.springframework.boot.SpringApplication.run;

/**
 * @Description
 * 1、如果没有使用@MapperScan注解，就需要在接口上增加@Mapper注解，否则MyBatis无法判断扫描哪些接口。
 * 2、注解@PreAuthorize用于控制方法是否具有访问权限，Spring Security默认是禁用的，如果想开启注解，需要在启动类上加@EnableMethodSecurity。
 * @Author <font color='blue'>zhangcm</font>
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableMethodCache(basePackages = "org.isite.wm")
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableTransactionManagement
@MapperScan(basePackages = {"org.isite.wm.admin.mapper"})
@SpringBootApplication
public class WmApplication {

    public static void main(String[] args) {
        run(WmApplication.class, args);
    }
}
