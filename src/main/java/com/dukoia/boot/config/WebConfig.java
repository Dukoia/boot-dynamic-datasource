package com.dukoia.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

/**
 * @Description: WebConfig
 * @Author: jiangze.He
 * @Date: 2021-07-21
 * @Version: v1.0
 */
@Configuration
@Component
@Order(Integer.MIN_VALUE)
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 实例化一个Locale拦截器
        // ... addInterceptors
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        // 设置获取Locale的参数是lang，默认情况下因为使用的是AcceptHeaderLocaleResolver，
        // 所以这个参数是Accept-Language
        localeInterceptor.setParamName("lang");
        // 忽略不合法的Locale，比如zh_CNs不是合法的，你将无法通过LocaleContextHolder.getLocale
        // 得到它
        localeInterceptor.setIgnoreInvalidLocale(true);
        registry.addInterceptor(localeInterceptor);
    }


    @Override
    protected void extendMessageConverters(
            List<HttpMessageConverter<?>> converters) {

        converters.stream()
                // 过滤出StringHttpMessageConverter类型实例
                .filter(MappingJackson2HttpMessageConverter.class::isInstance)
                .map(c -> (MappingJackson2HttpMessageConverter) c)
                // 这里将转换器的默认编码设置为utf-8
                .forEach(c -> c.setDefaultCharset(StandardCharsets.UTF_8));
    }


    /**
     *   localeResolver
     * @return
     */
    @Bean
    LocaleResolver localeResolver() {
        // 默认方法名称就是bean名称，若方法名过于随性需要在@Bean注解中指定为localeResolver
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        // 默认是简体中文
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }
}