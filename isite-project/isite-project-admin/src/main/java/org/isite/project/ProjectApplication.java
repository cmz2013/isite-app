package org.isite.project;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import static org.springframework.boot.SpringApplication.run;

/**
 * @Description 如果没有使用@MapperScan注解，就需要在接口上增加@Mapper注解，否则MyBatis无法判断扫描哪些接口。
 * @Author <font color='blue'>zhangcm</font>
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableMethodCache(basePackages = "org.isite.project")
@MapperScan(basePackages = {"org.isite.project.mapper"})
@SpringBootApplication
public class ProjectApplication {

    public static void main(String[] args) {
        run(ProjectApplication.class, args);
    }
}
