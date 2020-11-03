package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

    //http://localhost:8080/swagger-ui.html   Swagger访问源路径
    //http://localhost:8080/doc.html

    //配置swagger2核心配置 docket
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)         //指定Api类型为swagger2
                .apiInfo(apiInfo())                                     //用于定义Api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.imooc.controller")) //扫描controller
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("天天吃货，电商平台接口API")
                .contact(new Contact("imooc",
                        "http://www.shenzhijian.site",
                        "780974941@qq.com"))
                .description("电商购物平台网站提供API文档")
                .version("1.0.0")
                .termsOfServiceUrl("http:shenzhijian.site")
                .build();
    }
}
