package com.yuschool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(value = "com.yuschool.mapper") // 这个注解会扫描指定包下的所有接口作为MyBatis的Mapper
@SpringBootApplication // 这个注解标明一个SpringBoot的主配置类
public class YuSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuSchoolApplication.class, args);
    }

}
