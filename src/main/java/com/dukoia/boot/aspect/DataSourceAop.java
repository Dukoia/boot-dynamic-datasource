package com.dukoia.boot.aspect;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import lombok.extern.java.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * @Description: DataSourceAop
 * @Author: jiangze.He
 * @Date: 2021-06-11
 * @Version: v1.0
 */
@Aspect
@Component
@Order(0)
@Lazy(false)
@Log
public class DataSourceAop {

    private static final String MASTER = "master";

    private static final String SLAVE1 = "slave1";

    private static final String SLAVE2 = "slave2";

    private volatile Integer count = 0;
    final Random random = new Random(10);

    @Pointcut("execution(* com.dukoia.boot.service..*.*(..)) " +
            "|| execution(* com.baomidou.mybatisplus.extension.service..*.*(..))")
    public void checkArgs() {
    }


    @Before("checkArgs()")
    public void process(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException {
        String methodName = joinPoint.getSignature().getName();
        Class clazz = joinPoint.getTarget().getClass();
        if (clazz.isAnnotationPresent(DS.class)) {
            //获取类上注解
            return;
        }

        Class[] parameterTypes =
                ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        Method method = clazz.getMethod(methodName, parameterTypes);
        if (method.isAnnotationPresent(DS.class)) {
            return;
        }
        if (methodName.startsWith("get")
                || methodName.startsWith("count")
                || methodName.startsWith("find")
                || methodName.startsWith("list")
                || methodName.startsWith("select")
                || methodName.startsWith("check")
                || methodName.startsWith("page")) {
           int router = random.nextInt();
            if (router % 2 == 1) {
                log.info("当前执行的库：" + SLAVE1);
                DynamicDataSourceContextHolder.push(SLAVE1);

            } else {
                log.info("当前执行的库：" + SLAVE2);
                DynamicDataSourceContextHolder.push(SLAVE2);
            }
            count++;
            if (count == Integer.MAX_VALUE) {
                count = 0;
            }
        } else {
            log.info("当前执行的库：" + MASTER);
            DynamicDataSourceContextHolder.push(MASTER);
        }
    }

    @After("checkArgs()")
    public void afterAdvice() {
        DynamicDataSourceContextHolder.clear();
    }
}
