package com.dukoia.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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
    public Object error() throws InterruptedException {
        log.info("thread:{} come in", Thread.currentThread().getName());
        return "真好";
    }

    @GetMapping("/error1")
    public List<String> error1() throws InterruptedException {
        log.info("thread:{} come in", Thread.currentThread().getName());

        return Arrays.asList("真好");
    }
}
