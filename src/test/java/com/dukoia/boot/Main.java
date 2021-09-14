package com.dukoia.boot;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Description: Main
 * @Author: jiangze.He
 * @Date: 2021-07-19
 * @Version: v1.0
 */
public class Main {

    public static final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        long l = System.currentTimeMillis();
        MyListener myListener = new MyListener();
        EasyExcel.read("D:\\gold.xlsx", BatchGold.class, myListener).sheet(0).doRead();
        System.out.println("耗时" + (System.currentTimeMillis() - l));
//        for (BatchGold batchGold : myListener.list) {
//            System.out.println(batchGold);
//        }

//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//        build.read(readSheet);

//        String total = "857";
//        String success = "109";
//        Long fail = 3L;
//
//        System.out.println((fail + Long.valueOf(success)) == Long.valueOf(total));
//
//        double d = (fail + Long.valueOf(success)) / Double.valueOf(total);
//        System.out.println(String.format("%.2f", d * 100));

        MyClassA myClassA = new MyClassA();
        myClassA.setName(10086);
        MyClassB myClassB = new MyClassB();
        MyInterface myClass1 = new MyClassB();
        System.out.println(myClassA.getSum());
        System.out.println(testInterface(myClassA));
        System.out.println(testInterface(myClassB));
        System.out.println(testInterface(myClass1));
//        future();
//        System.out.println(isUnique("nihaoa"));
//        future2();
//        List<Integer> integers = Arrays.asList(1, 2, 1, 5, 1);
//        System.out.println(Stream.of(integers) .count());
//        System.out.println(773 << 1 & 773);
//        ArrayList<UserInfo> userInfos = new ArrayList<>();
//        userInfos.add(new UserInfo());
//        long count = userInfos.stream().map(UserInfo::getId).distinct().count();
//        int t = 773;
//        for (int i = 1; i <= 773; i++) {
//            if (773 % i == 0) {
//                System.out.println(i);
//            }
//        }
    }

    public static String testInterface(MyInterface myInterface) {

        if (myInterface instanceof MyClassA){
            MyClassA a = (MyClassA) myInterface;
            Integer name = a.getName();
            System.out.println(name);
        }

        return myInterface.classMethod();
    }

    private static void future2() throws InterruptedException, java.util.concurrent.ExecutionException {
        CompletableFuture<String> string = CompletableFuture.supplyAsync(() -> {
            return queryCode("hello");
        }, executorService);

        CompletableFuture<String> future1 = string.thenApply((s) -> {
            return queryCode(s + "then");
        });

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("11111");
        });
        CompletableFuture<Void> a = CompletableFuture.allOf(future, string);
        a.join();
        System.out.println(future1.get());
        System.out.println(string.get());
    }


    public static boolean isUnique(String astr) {
        int bitmap = 0;
        for (char c : astr.toCharArray()) {
            int pos = c - 'a';
            if ((bitmap & (1 << pos)) != 0) {
                return false;
            }
            bitmap |= (1 << pos);
        }
        return true;
    }

    private static void future() throws InterruptedException, ExecutionException {
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        });
        CompletableFuture<? extends Serializable> handle = cfQuery.handle((x, y) -> {
            if (y != null) {
                y.printStackTrace();
                return 0;
            }
            return x + 100;
        });
        String i = (String) handle.get();
        System.out.println(i);
        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync(Main::fetchPrice);
        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        CompletableFuture.allOf(cfFetch, cfQuery).join();
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(2000);
    }

    static String queryCode(String name) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name;
    }

    static Double fetchPrice(String code) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 5 + Math.random() * 20;
    }
}
