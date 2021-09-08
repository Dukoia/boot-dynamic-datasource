package com.dukoia.boot;

/**
 * Context 使用 ThreadLocal 存储数据时实现 AutoCloseable
 * 使用 try-with-resources 可不用手动移除 ThreadLocal 中的数据
 * 无论是否发生异常 ThreadLocal 会自动移除 在 finally 前也自动移除
 *
 * @Description: ThreadLocalTest
 * @Author: jiangze.He
 * @Date: 2021-08-30
 * @Version: v1.0
 */
public class ThreadLocalTest {

    public static void main(String[] args) {

        System.out.println((13 / 51f) * 100);
        System.out.println(Math.ceil((17 / Float.valueOf(51)) * 100));
//        tryCatch();
//        System.out.println("==========================");
//        tryWithResources();
    }

    /**
     * use try-catch
     */
    private static void tryCatch() {
        try {
            StringThreadLocalContext s = new StringThreadLocalContext("niko");
            System.out.println(StringThreadLocalContext.currentUser());
            int i = 1 / 0;
            System.out.println(StringThreadLocalContext.currentUser()); // 不打印
        } catch (Exception e) {
            System.out.println(StringThreadLocalContext.currentUser()); // 有值
            System.out.println(e);
        } finally {
            System.out.println(StringThreadLocalContext.currentUser()); // 有值
        }
        System.out.println(StringThreadLocalContext.ctx.get()); // 有值
    }


    /**
     * use tryWithResources
     */
    private static void tryWithResources() {
        try (StringThreadLocalContext s = new StringThreadLocalContext("niko1")) {
            System.out.println(StringThreadLocalContext.currentUser());
            int i = 1 / 0;
            System.out.println(StringThreadLocalContext.currentUser()); // 不打印
        } catch (Exception e) {
            System.out.println(StringThreadLocalContext.currentUser()); // 为空
            System.out.println(e);
        } finally {
            System.out.println(StringThreadLocalContext.currentUser()); // 为空
        }
        System.out.println(StringThreadLocalContext.ctx.get()); // 为空
    }
}
