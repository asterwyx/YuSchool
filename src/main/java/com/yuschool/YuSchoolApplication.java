package com.yuschool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.yuschool.mapper")
@SpringBootApplication
public class YuSchoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(YuSchoolApplication.class, args);
    }
}
