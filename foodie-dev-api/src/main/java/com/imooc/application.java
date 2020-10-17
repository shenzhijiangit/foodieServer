package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描通用的mapper包
@MapperScan(basePackages = "com.imooc.mapper")
public class application {
    public static void main(String[] args) {
        SpringApplication.run(application.class,args);
    }
}
