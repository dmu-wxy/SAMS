package com.wxy.sams.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//要明确controller 是什么请求get.post....
//接口文档在/swagger-ui.html中
//@Configuration
//@EnableSwagger2
public class Swagger2Config {
    @Bean
    Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wxy.sams.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .description("接口文档描述信息")
                        .title("浏览动物信息系统接口文档")
                        .contact(new Contact("meteor","www.smartdog.top","2290502632@qq.com"))
                        .version("v1.0")
                        .license("Apache2.0").build());

    }
}
