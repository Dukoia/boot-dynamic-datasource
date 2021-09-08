package com.dukoia.boot.batch;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import java.io.FileInputStream;
import java.util.List;
import java.util.concurrent.*;

/**
 * split -l 100000 largeFile.txt -d -a 4 smallFile_
 *
 * @Description: PushLargeDate
 * @Author: jiangze.He
 * @Date: 2021-07-27
 * @Version: v1.0
 */
@Slf4j
public class PushLargeDate {

    private static final ExecutorService executorService = new ThreadPoolExecutor(5,
            10,
            60L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10 * 10000),
            new ThreadFactoryBuilder().setNameFormat("push-data-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        data("D:\\accounts.json");
    }

    public static void data(String file) {
        try (LineIterator iterator = IOUtils.lineIterator(new FileInputStream(file), "UTF-8")) {
            // 存储每个任务执行的行数
            List<String> lines = Lists.newArrayList();
            // 存储异步任务
            List<ConvertTask> tasks = Lists.newArrayList();
            while (iterator.hasNext()) {
                String line = iterator.nextLine();
                lines.add(line);
                // 设置每个线程执行的行数
                if (lines.size() == 10) {
                    // 新建异步任务，注意这里需要创建一个 List
                    tasks.add(new ConvertTask(Lists.newArrayList(lines)));
                    lines.clear();
                }
                if (tasks.size() == 10) {
                    asyncBatchExecuteTask(tasks);
                }

            }
            // 文件读取结束，但是可能还存在未被内容
            tasks.add(new ConvertTask(Lists.newArrayList(lines)));
            // 最后再执行一次
            asyncBatchExecuteTask(tasks);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    /**
     * 批量执行任务
     *
     * @param tasks
     */
    private static void asyncBatchExecuteTask(List<ConvertTask> tasks) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(tasks.size());
        for (ConvertTask task : tasks) {
            task.setCountDownLatch(countDownLatch);
            executorService.submit(task);
        }
        // 主线程等待异步线程 countDownLatch 执行结束
        countDownLatch.await();
        // 清空，重新添加任务
        tasks.clear();
    }

    /**
     * 异步任务
     * 等数据导入完成之后，一定要调用 countDownLatch.countDown()
     * 不然，这个主线程将会被阻塞，
     */
    @Slf4j
    private static class ConvertTask implements Runnable {

        private CountDownLatch countDownLatch;

        private List<String> lines;

        public ConvertTask(List<String> lines) {
            this.lines = lines;
        }

        public void setCountDownLatch(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                for (String line : lines) {
                    convertToDB(line);
                }
            } finally {
                countDownLatch.countDown();
            }
        }

        private void convertToDB(String line) {
            log.info("行数据:{}", line);
        }
    }
}
