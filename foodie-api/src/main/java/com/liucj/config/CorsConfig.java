package com.liucj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 设置前端跨域配置
 */
@Configuration
public class CorsConfig {

    public CorsConfig() {
    }

    @Bean
    public CorsFilter corsFilter() {
        // 1. 添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin("http://localhost:9999");
        config.addAllowedOrigin("http://shop.z.mukewang.com:8080");
        config.addAllowedOrigin("http://center.z.mukewang.com:8080");
        config.addAllowedOrigin("http://shop.z.mukewang.com");
        config.addAllowedOrigin("http://center.z.mukewang.com");
        //1) 允许的域,不要写*，否则cookie就无法使用了
        //config.addAllowedOrigin("http://www.leyou.com");
//        config.addAllowedOrigin("*");

        // 设置是否发送cookie信息
        config.setAllowCredentials(true);

        // 设置允许请求的方式
        config.addAllowedMethod("*");

        // 设置允许的header
        config.addAllowedHeader("*");

        // 2. 为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);

        // 3. 返回重新定义好的corsSource
        return new CorsFilter(corsSource);
    }

}
