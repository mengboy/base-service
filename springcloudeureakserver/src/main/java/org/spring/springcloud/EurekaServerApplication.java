package org.spring.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Spring Boot Eureka Server 应用启动类
 *
 */
@EnableEurekaServer     // Eureka Server 标识
@SpringBootApplication  // Spring Boot 应用标识
public class EurekaServerApplication {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(EurekaServerApplication.class,args);
    }
}
