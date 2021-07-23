package com.dukoia.boot.config;

import com.dukoia.boot.handler.UserInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @link https://blog.csdn.net/qq_41871088/article/details/112217233?utm_source=app&app_version=4.11.0&code=app_1562916241&uLinkId=usr1mkqgl919blen
 * @author jiangze.he
 */
@Configuration
@Component
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Bean
    public UserInterceptor userInterceptor(){
        return new UserInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor()).addPathPatterns("/login");
        registry.addInterceptor(userInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}