package com.dukoia.boot.cache;

import com.dukoia.boot.content.ThreadPoolConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: AsyncExecutor
 * @Author: jiangze.He
 * @Date: 2021-07-19
 * @Version: v1.0
 */
@Slf4j
public class AsyncExecutor {
    /**
     * 默认最大并发数<br>,CPU核数*2
     */
    public static final int DEFAULT_MAX_CONCURRENT = ThreadPoolConstant.DEFAULT_MAX_CONCURRENT;
    public static final int DEFAULT_CORE_CONCURRENT = ThreadPoolConstant.DEFAULT_CORE_CONCURRENT;
    public static final int CORE_CONCURRENT = ThreadPoolConstant.CORE_CONCURRENT;

    /**
     * 线程工厂名称
     */
    private static final ThreadFactory FACTORY = new CustomThreadFactory();

    private static final ThreadFactory SCHEDULE_FACTORY = new ScheduleThreadFactory();

    /**
     * 默认队列大小
     * 5000 to 50， 减少内存占用，防止进行fullgc，为了高性能，光放在队列里不执行，性能也低
     */
    private static final int DEFAULT_SIZE = DEFAULT_MAX_CONCURRENT * 3;

    /**
     * 默认线程存活时间
     */
    private static final long DEFAULT_KEEP_ALIVE = 10L;

    /**
     * NewEntryServiceImpl.java:689
     * Executor
     */
    private static ExecutorService executor;

    public static ScheduledExecutorService scheduledExecutor;

    /**
     * 执行队列
     */
    private static BlockingQueue<Runnable> executeQueue = new ArrayBlockingQueue<>(DEFAULT_SIZE);

    static {
        // 创建 Executor
        // 此处默认最大值改为处理器数量的 4 倍
        try {
            executor = new ThreadPoolExecutor(DEFAULT_CORE_CONCURRENT,
                    DEFAULT_MAX_CONCURRENT, DEFAULT_KEEP_ALIVE,
                    TimeUnit.SECONDS, executeQueue, FACTORY, new ThreadPoolExecutor.AbortPolicy());
            // 关闭事件的挂钩
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    AsyncExecutor.log.info("AsyncProcessor shutting down.");
                    shutdownListen(executor);
                    AsyncExecutor.log.info("AsyncProcessor shutdown complete.");
                }

                private void shutdownListen(ExecutorService executor) {
                    executor.shutdown();
                    try {
                        // 等待1秒执行关闭
                        if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
                            AsyncExecutor.log.error("AsyncProcessor shutdown immediately due to wait timeout.");
                            executor.shutdownNow();
                        }
                    } catch (InterruptedException e) {
                        AsyncExecutor.log.error("AsyncProcessor shutdown interrupted.");
                        executor.shutdownNow();
                    }
                }

            }));
        } catch (Exception e) {
            log.error("AsyncProcessor ExecutorService init error.", e);
            throw new ExceptionInInitializerError(e);
        }

        try {
            scheduledExecutor = new ScheduledThreadPoolExecutor(CORE_CONCURRENT, SCHEDULE_FACTORY, new CustomRejectedExecutionHandler());
        } catch (Exception e) {
            log.error("AsyncProcessor ScheduledExecutorService init error.", e);
        }
    }

    public static ExecutorService customExecutor(int coreSize, long keepAliveTime) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize, coreSize, keepAliveTime,
                TimeUnit.SECONDS, executeQueue, FACTORY, new CustomRejectedExecutionHandler());
        executor.allowCoreThreadTimeOut(true);
        return executor;
    }

    /**
     * 此类型无法实例化
     */
    private AsyncExecutor() {
    }

    /**
     * 执行任务，不管是否成功<br>
     * 其实也就是包装以后的 {@link} 方法
     *
     * @param task
     * @return
     */
    public static boolean executeTask(Runnable task) {
        try {
            executor.execute(task);
        } catch (Exception e) {
            log.error("Task executing was rejected.", e);
            throw e;
        }

        return true;
    }

    /**
     * 提交任务，并可以在稍后获取其执行情况<br>
     * 当提交失败时，会抛出 {@link }
     *
     * @param task
     * @return
     */
    public static <T> Future<T> submitTask(Callable<T> task) {

        try {
            return executor.submit(task);
        } catch (Exception e) {
            log.error("Task executing was rejected.", e);
            throw e;
        }
    }

    /**
     * 自定义创建线程的工厂  自定义线程名称
     */
    private static class CustomThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = AsyncExecutor.class.getSimpleName() + "-threadPool-" + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }

    /**
     * 自定义创建线程的工厂  自定义线程名称
     */
    private static class ScheduleThreadFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName =
                    AsyncExecutor.class.getSimpleName() + "-ScheduleThreadPool-" + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }

    /**
     * 自定义拒绝策略  如果队列已满 就堵塞继续等待 直到队列有空闲
     */
    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                // 核心改造点，由blockingqueue的offer改成put阻塞方法
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                log.error("CustomRejectedExecutionHandler Exception.", e);
            }
        }
    }

}

