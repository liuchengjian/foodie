package com.liucj.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Swagger配置文件
 * 通过 http://localhost:端口号/swagger-ui.html访问
 *  http://localhost:8088/swagger-ui.html
 *  http://localhost:8088/doc.html
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    //配置swagger2核心培训 docket
    private static final String VERSION ="1.0.0";
    private static final String AUTHOR ="liucj";
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liucj.as.api.controller")) //controller的包名
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(ApiIgnore.class)
                .tags(new Tag("Account","账号模块"))
                .tags(new Tag("Category","商品类别"))
                .tags(new Tag("HiConfig","配置中心"));
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //设置文档标题
                .title("天天吃货 API文档")
                .description("JAVA架构")
                .version(VERSION)
                .contact(new Contact(AUTHOR,"http://www.baidu.com","627107345@qq.com"))
                .build();
    }
}
