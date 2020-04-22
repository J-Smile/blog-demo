package com.smile.config;

import com.smile.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 * @author: Smile
 * @description:
 * @create: 2020-04-02 10:20
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private UserInterceptor userArgumentResolver;

//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(userArgumentResolver).addPathPatterns("/**");
//        super.addInterceptors(registry);
//
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods("*")
                .maxAge(3600);
    }

}
