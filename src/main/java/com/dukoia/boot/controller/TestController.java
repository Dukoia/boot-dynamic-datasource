package com.dukoia.boot.controller;

import com.dukoia.boot.common.BizException;
import com.dukoia.boot.common.ResponseCodeI18n;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TestController
 * @Author: jiangze.He
 * @Date: 2021-08-25
 * @Version: v1.0
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/error")
    public String error() throws InterruptedException {
        log.info("thread:{} come in", Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(20);

        return "hhhh";
    }
}
