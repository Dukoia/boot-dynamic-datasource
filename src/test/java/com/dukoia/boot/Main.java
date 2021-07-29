package com.dukoia.boot;

import com.dukoia.boot.model.UserInfo;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Description: Main
 * @Author: jiangze.He
 * @Date: 2021-07-19
 * @Version: v1.0
 */
public class Main {
    public static void main(String[] args) throws Exception {
//        future();
//        System.out.println(isUnique("nihaoa"));
//        future2();

        System.out.println(773 << 1 & 773);
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

    private static void future2() throws InterruptedException, java.util.concurrent.ExecutionException {
        CompletableFuture<String> string = CompletableFuture.supplyAsync(() -> {
            return "hello";
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

    private static void future() throws InterruptedException {
        // 第一个任务:
        CompletableFuture<String> cfQuery = CompletableFuture.supplyAsync(() -> {
            return queryCode("中国石油");
        });

        // cfQuery成功后继续执行下一个任务:
        CompletableFuture<Double> cfFetch = cfQuery.thenApplyAsync(Main::fetchPrice);
        // cfFetch成功后打印结果:
        cfFetch.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        CompletableFuture.allOf(cfFetch, cfQuery);
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