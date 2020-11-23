package com.lxy.cn.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//注册拦截器的配置类
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //  /**表示拦截所有请求
        String[] addPath = {"/**"};
        // 放行getStudent和getUser两个请求：其中getUser请求没有对应的处理器
        String[] excludePath = {"/login","/user","/add","/additem","/return","/delete","/edit","/css/login.css","/css/reset.css","/css/index.css","/css/details.css"};
        registry.addInterceptor(new InterceptorByInterface()).addPathPatterns(addPath).excludePathPatterns(excludePath);
    }
}

