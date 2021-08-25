package com.dukoia.boot.controller;


import com.dukoia.boot.common.AccessLimit;
import com.dukoia.boot.common.BizException;
import com.dukoia.boot.common.ResponseCodeI18n;
import com.dukoia.boot.common.Result;
import com.dukoia.boot.model.UserInfo;
import com.dukoia.boot.service.IUserInfoService;
import com.dukoia.boot.thread.MDCThreadPoolExecutor;
import com.dukoia.boot.utils.JacksonUtil;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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


    private List<UserInfo> list;

    private static final MDCThreadPoolExecutor MDCEXECUTORS =
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

    private Lock lock = new ReentrantLock();
    private Integer count = 0;

    @GetMapping("/list")
    public Object list() throws InterruptedException {
        List<UserInfo> list = Collections.EMPTY_LIST;
        log.info("request count:{}", count++);
        boolean flag = lock.tryLock();
        log.info("thread:{},is lock:{},", Thread.currentThread().getName(), flag);
        if (flag) {
            log.info("获取锁成功:{}", lock.toString());
            try {
                TimeUnit.SECONDS.sleep(20);
                list = iUserInfoService.list();
            } catch (Exception e) {
                log.error(" 异常", e);
            } finally {
                if (flag) {
                    log.info("thread:{}解锁", Thread.currentThread().getName());
                    lock.unlock();
                }
            }
        } else {
            log.info("尝试加锁失败");
            return Arrays.asList("等待");
        }
        return list;
    }

    @PostConstruct
    protected void init() {
        list = iUserInfoService.list();
        log.info(JacksonUtil.toJson(list));
    }


    /**
     * 获取request参数
     *
     * @return map集合
     */
    protected Map<String, Object> getParameter(HttpServletRequest request) {
        Map<String, Object> ht = new HashMap<String, Object>();
        String name = "", values = "";
        for (Enumeration<String> names = request.getParameterNames(); names
                .hasMoreElements(); ht.put(name, values)) {
            name = (String) names.nextElement();
            values = request.getParameter(name);
        }
        return ht;
    }

    @GetMapping(value = "/string")
//    @AccessLimit(sec = 10)
    public Result string(HttpServletRequest request) throws InterruptedException {

        Map<String, Object> parameter = getParameter(request);
        log.info("parameter:{}", JacksonUtil.toJson(parameter));

        RateLimiter rateLimiter = RateLimiter.create(1.0);
        TimeUnit.SECONDS.sleep(20);
        log.info("urI:{}", request.getRequestURI());
        log.info("url:{}", request.getRequestURL().toString());
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getSession().getServletContext().getContextPath()).toString();
        log.info("tempContextUrl:{}", tempContextUrl);
        log.info("============");
//        ThreadPoolUtils.execute(() ->{
//
//            log.info("测试日志id");
//        });
        MDCEXECUTORS.execute(() -> {
            log.info("测试日志id2");
        });
        return Result.success("哈哈");
    }

    @GetMapping("/error")
    public String error(@Param(value = "id") Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "http://bbs-test-tmp.picovr.com/sso/sso.html";
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", url);
        response.flushBuffer();
        return "redirect:" + url;
    }

    @GetMapping("/error1")
    public String error(@Param(value = "id") Integer id) {


        if (0 == id) {
            throw new BizException(ResponseCodeI18n.FAILED);
        }
        int i = 1 / 0;
        return "hhhh";
    }


}

