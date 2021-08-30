package com.dukoia.boot;

import java.io.Closeable;

/**
 * @Description: StringThreadLocalContent
 * @Author: jiangze.He
 * @Date: 2021-08-30
 * @Version: v1.0
 */
public class StringThreadLocalContext implements AutoCloseable{


    static final ThreadLocal<String> ctx = new ThreadLocal<>();

    public StringThreadLocalContext(String user) {
        ctx.set(user);
    }

    public static String currentUser() {
        return ctx.get();
    }


    @Override
    public void close() throws Exception {
        ctx.remove();
    }
}
