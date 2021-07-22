package com.dukoia.boot.common;

import com.dukoia.boot.enums.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @Description: RestExceptionHandler
 * @Author: jiangze.He
 * @Date: 2021-07-20
 * @Version: v1.0
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
    /**
     * 默认全局异常处理。
     *
     * @param e the e
     * @return ResultData
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return Result.fail(ReturnCode.RC500.getCode(), e.getMessage());
    }

    /**
     * -------- 指定异常处理方法 --------
     **/
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Result error(NullPointerException e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return Result.fail(ReturnCode.RC500);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public Result error(IndexOutOfBoundsException e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return Result.fail(ReturnCode.RC500);
    }

    /**
     * -------- 自定义定异常处理方法 --------
     **/
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Result error(BizException e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return Result.fail(ReturnCode.RC500.getCode(), e.getMessage());
    }
}
