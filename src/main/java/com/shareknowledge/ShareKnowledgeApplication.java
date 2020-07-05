package com.shareknowledge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan(value = "com.shareknowledge.mapper")
@SpringBootApplication
public class ShareKnowledgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShareKnowledgeApplication.class, args);
    }

}
