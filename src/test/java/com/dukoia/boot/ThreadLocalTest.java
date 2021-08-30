package com.dukoia.boot;

/**
 * Context 使用 ThreadLocal 存储数据时实现 AutoCloseable
 * 使用 try with source 可不用手动移除 ThreadLocal 中的数据
 * 无论是否发生异常 ThreadLocal 会自动移除 在 finally 前也自动移除
 *
 * @Description: ThreadLocalTest
 * @Author: jiangze.He
 * @Date: 2021-08-30
 * @Version: v1.0
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        try (StringThreadLocalContext s = new StringThreadLocalContext("niko")) {
            System.out.println(StringThreadLocalContext.currentUser());
            System.out.println(StringThreadLocalContext.currentUser());
        } catch (Exception e) {
            System.out.println(StringThreadLocalContext.currentUser());
            System.out.println(e);
        } finally {
            System.out.println(StringThreadLocalContext.currentUser());
        }
        System.out.println(StringThreadLocalContext.ctx.get());
    }
}
