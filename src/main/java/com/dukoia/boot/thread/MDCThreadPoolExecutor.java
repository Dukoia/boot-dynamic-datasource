package com.dukoia.boot.thread;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @Description: MDCThreadPoolExecutor
 * @Author: jiangze.He
 * @Date: 2021-07-27
 * @Version: v1.0
 */
@Slf4j
public class MDCThreadPoolExecutor extends ThreadPoolExecutor {


    public MDCThreadPoolExecutor(int corePoolSize,
                                 int maximumPoolSize,
                                 long keepAliveTime,
                                 TimeUnit unit,
                                 BlockingQueue<Runnable> workQueue,
                                 ThreadFactory threadFactory,
                                 RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public MDCThreadPoolExecutor(int corePoolSize,
                                 int maximumPoolSize,
                                 long keepAliveTime,
                                 TimeUnit unit,
                                 BlockingQueue<Runnable> workQueue,
                                 ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public MDCThreadPoolExecutor(int corePoolSize,
                                 int maximumPoolSize,
                                 long keepAliveTime,
                                 TimeUnit unit,
                                 BlockingQueue<Runnable> workQueue,
                                 RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public MDCThreadPoolExecutor(int corePoolSize,
                                 int maximumPoolSize,
                                 long keepAliveTime,
                                 TimeUnit unit,
                                 BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

//    public void showInfo(){
//        long taskCount = super.getTaskCount();
//        long count = super.getCompletedTaskCount();
//        int activeCount = super.getActiveCount();
//        int size = super.getQueue().size();
//        log.info("taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",taskCount,count,activeCount,size);
//    }

    @Override
    public void execute(final Runnable runnable) {
        // 获取父线程MDC中的内容，必须在run方法之前，否则等异步线程执行的时候有可能MDC里面的值已经被清空了，这个时候就会返回null
        final Map<String, String> context = MDC.getCopyOfContextMap();
        super.execute(new Runnable() {
            @Override
            public void run() {
                // 将父线程的MDC内容传给子线程
                MDC.setContextMap(context);
                try {
                    // 执行异步操作
                    runnable.run();
                } finally {
                    // 清空MDC内容
                    MDC.clear();
                }
            }
        });
    }
}
