package com.liucj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描mybatis 通用mapper所在的包
@MapperScan(basePackages = "com.liucj.mapper")
//扫描所有包,以及组件包
@ComponentScan(basePackages = {"com.liucj","org.n3r.idworker"})
@EnableScheduling       // 开启定时任务
public class Application {
    //psvm
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
