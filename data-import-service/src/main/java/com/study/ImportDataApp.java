package com.study;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableEurekaClient     // Eureka Server 标识
@SpringBootApplication  // Spring Boot 应用标识
@EnableTransactionManagement
@MapperScan(basePackages = "com.study.mapper")
public class ImportDataApp {
    public static void main(String[] args){
        SpringApplication.run(ImportDataApp.class);
    }

}
