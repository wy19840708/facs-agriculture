package com.facs.agriculture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description 拦截器配置
 * @Author luke
 * @Date 2018-02-02
 */

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter{

    @Bean
    RequestIntercept requestIntercept() {
        return new RequestIntercept();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestIntercept()).addPathPatterns("/*");
    }

}
