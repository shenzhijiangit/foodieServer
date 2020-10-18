package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描通用的mapper包
@MapperScan(basePackages = "com.imooc.mapper")
//扫描所有包以及组件包
@ComponentScan(basePackages = {"org.n3r.idworker","com.imooc"})
public class application {
    public static void main(String[] args) {
        SpringApplication.run(application.class,args);
    }
}
