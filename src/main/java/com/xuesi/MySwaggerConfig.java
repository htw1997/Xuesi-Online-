package com.xuesi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration      //让Spring来加载该类配置
@EnableSwagger2     //启用Swagger2
public class MySwaggerConfig {

    //创建文档说明
    @Bean
    public ApiInfo createAI() {
        ApiInfo apiInfo = new ApiInfoBuilder().title("xuesi").description("学思网对外开放接口")
                .contact(new Contact("Java组", "http://www.17feri.top",
                        "18337159695@163.com")).build();
        return apiInfo;
    }

    @Bean
    //创建Swagger扫描信息
    public Docket createD() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createAI()).select()
                .apis(RequestHandlerSelectors.basePackage("com.xuesi.controller")).build();
    }
}