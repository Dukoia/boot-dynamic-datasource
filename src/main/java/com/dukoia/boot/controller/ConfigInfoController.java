package com.dukoia.boot.controller;


import com.dukoia.boot.common.AccessLimit;
import com.dukoia.boot.common.BizException;
import com.dukoia.boot.common.ResponseCodeI18n;
import com.dukoia.boot.common.Result;
import com.dukoia.boot.service.IUserInfoService;
import com.dukoia.boot.thread.MDCThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * <p>
 * config_info 前端控制器
 * </p>
 *
 * @author Dukoia
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/config")
@Slf4j
public class ConfigInfoController {

    @Autowired
    IUserInfoService iUserInfoService;

    private static final MDCThreadPoolExecutor MDCEXECUTORS=
            new MDCThreadPoolExecutor(1,
                    10,
                    60,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<Runnable>(600),
                    Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 打印日志，并且重启一个线程执行被拒绝的任务
            log.error("Task：{},rejected from：{}", r.toString(), executor.toString());
            // 直接执行被拒绝的任务，JVM另起线程执行
            r.run();
        }
    });

    @GetMapping("/list")
    public Object list() {
        return iUserInfoService.list();
    }

    @GetMapping(value = "/string")
    @AccessLimit(sec = 10)
    public Result string() {
        log.info("============");
//        ThreadPoolUtils.execute(() ->{
//
//            log.info("测试日志id");
//        });
        MDCEXECUTORS.execute(() ->{
            log.info("测试日志id2");
        });
        return Result.success( "哈哈");
    }

    @GetMapping("/error")
    public String error(@Param(value = "id")Integer id) {
        if (0 == id){
            throw new BizException(ResponseCodeI18n.FAILED);
        }
        int i = 1 / 0;
        return "hhhh";
    }


}

