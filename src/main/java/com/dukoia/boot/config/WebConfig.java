//package com.dukoia.boot.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.StringHttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Locale;
//
///**
// * @Description: WebConfig
// * @Author: jiangze.He
// * @Date: 2021-07-21
// * @Version: v1.0
// */
//@Configuration
//@EnableWebMvc
//public class WebConfig extends WebMvcConfigurationSupport {
//
//
//    @Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
//        converter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", Charset.forName("UTF-8"))));
//        converter.setWriteAcceptCharset(false);
//        return converter;
//    }
//
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//        converters.add(longToStringConverter());
//        // 这里必须加上加载默认转换器，不然bug玩死人，并且该bug目前在网络上似乎没有解决方案
//        // 百度，谷歌，各大论坛等。你可以试试去掉。如果这段代码，spring启动后消息转换器只有StringHttpMessageConverter这一个
//        super.addDefaultHttpMessageConverters(converters);
//    }
//
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(false);
//    }
//
//
//    /**
//     * localeResolver
//     *
//     * @return
//     */
//    @Bean
//    LocaleResolver localeResolver() {
//        // 默认方法名称就是bean名称，若方法名过于随性需要在@Bean注解中指定为localeResolver
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        // 默认是简体中文
//        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
//        return localeResolver;
//    }
//
//    /**
//     * 将返回给前端的Long和long，统一转化成字符串
//     *
//     * @return
//     */
//    @Bean
//    public MappingJackson2HttpMessageConverter longToStringConverter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper mapper = new ObjectMapper();
//        SimpleModule simpleModule = new SimpleModule();
//        //Long
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        //long
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        mapper.registerModule(simpleModule);
//        converter.setObjectMapper(mapper);
//        return converter;
//    }
//
//}
