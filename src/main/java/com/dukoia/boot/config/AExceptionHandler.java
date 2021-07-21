package com.dukoia.boot.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: AExceptionHandler
 * @Author: jiangze.He
 * @Date: 2021-07-21
 * @Version: v1.0
 */
@Component
public class AExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        response.setCharacterEncoding("utf-8");
        return null;
    }
}