package com.dukoia.boot.common;

import java.lang.annotation.*;

/**
 * @Description: AccessLimit
 * @Author: jiangze.He
 * @Date: 2021-07-23
 * @Version:v1.0
 */
@Inherited
@Documented
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    //标识 指定sec时间段内的访问次数限制
    int limit() default 5;
    //标识 时间段
    int sec() default 5;
}
