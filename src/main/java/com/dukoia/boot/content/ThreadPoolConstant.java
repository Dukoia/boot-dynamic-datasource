package com.dukoia.boot.content;

/**
 * @Description: ThreadPoolConstant
 * @Author: jiangze.He
 * @Date: 2021-07-19
 * @Version: v1.0
 */
public class ThreadPoolConstant {
    /**
     * 默认最大并发数<br>,CPU核数*2
     */
    public static final int DEFAULT_MAX_CONCURRENT = Runtime.getRuntime().availableProcessors() * 4;
    public static final int DEFAULT_CORE_CONCURRENT = Runtime.getRuntime().availableProcessors() * 2;
    public static final int CORE_CONCURRENT = Runtime.getRuntime().availableProcessors();
}
