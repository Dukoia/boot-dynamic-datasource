package com.dukoia.boot.common;

import com.dukoia.boot.utils.JacksonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @Description: ResponseAdvice
 * @Author: jiangze.He
 * @Date: 2021-07-20
 * @Version: v1.0
 */
@RestControllerAdvice
@Slf4j
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        log.info("进来了");
        if (o instanceof String) {
            // 由于某种原因导致的我菜,故在这里将原有的
            // return objectMapper.writeValueAsString(Result.success(o));
            // 改写成下面的逻辑

            ServletServerHttpResponse response = (ServletServerHttpResponse)serverHttpResponse;
            OutputStream body = response.getBody();
            body.write(JacksonUtil.toJson(Result.success(o)).getBytes());
            response.getServletResponse().setContentType(MediaType.APPLICATION_JSON.toString());
            response.flush();
            response.close();
            return null;
        }
        if(o instanceof Result){
            return (Result) o;
        }
        return Result.success(o);
    }
}
