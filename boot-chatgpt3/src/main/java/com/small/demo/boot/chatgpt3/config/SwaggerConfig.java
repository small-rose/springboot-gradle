package com.small.demo.boot.chatgpt3.config;


import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: TODO 功能角色说明：
 * TODO 描述：
 * @author: 张小菜
 * @date: 2021/12/14 22:43
 * @version: v1.0
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    @Autowired
    Environment environment;

    @Bean
    public Docket createRestApi() throws UnknownHostException {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.small.demo"))
                //.apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                //.paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                //.paths(Predicates.not(PathSelectors.regex("/error.*")))
                .paths(PathSelectors.any())
                .build();
    }
    public ApiInfo apiInfo() throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        String contextPath = environment.getProperty("server.servlet.context-path");
        String port = environment.getProperty("server.port");
        return new ApiInfoBuilder()
                .title("bp-excel api文档")
                .description("bp-excel api文档")
                .termsOfServiceUrl("http://"+ip+":"+port+""+contextPath+"/swagger-ui.html, http://"+ip+":"+port+""+contextPath+"/doc.html")
                .version("version 1.0")
                .build();
    }
}
