package com.dukoia.boot.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Description: AbstractCacheManager
 * @Author: jiangze.He
 * @Date: 2021-07-19
 * @Version: v1.0
 */
@Slf4j
public abstract class AbstractCacheManager<K, V> {

    /**
     * 缓存自动刷新周期
     */
    protected int refreshDuration;

    /**
     * 缓存刷新周期时间格式
     */
    protected TimeUnit durationTimeUnit;

    /**
     * 缓存过期时间
     */
    protected int expiredDration;

    /**
     * 缓存最大容量
     */
    protected long cacheMaximumSize;

    private LoadingCache<K, V> cache;
    private static final ListeningExecutorService BACKGROUND_REFRESH_POOLS =
            MoreExecutors.listeningDecorator(AsyncExecutor.customExecutor(Runtime.getRuntime().availableProcessors() + 1, 10L));

    protected abstract void initCacheFields();

    protected abstract V getValueWhenExpire(K key) throws Exception;

    public abstract V getValue(K key);

    private synchronized LoadingCache<K, V> getCache() {

        /**
         * <p>
         * expireAfterAccess: 当缓存项在指定的时间段内没有被读或写就会被回收。
         * </p>
         * <p>
         * expireAfterWrite：当缓存项在写入之后指定的时间段内失效。
         * </p>
         * <p>
         * refreshAfterWrite：当缓存项上一次更新操作之后的多久会被刷新。
         * </p>
         */
        if (cache == null) {
            initCacheFields();
            cache =
                    CacheBuilder.newBuilder().maximumSize(cacheMaximumSize)
                            .expireAfterWrite(expiredDration, durationTimeUnit)
                            .refreshAfterWrite(refreshDuration, durationTimeUnit)
                            .build(new CacheLoader<K, V>() {
                                @Override
                                public V load(K key) throws Exception {
                                    return getValueWhenExpire(key);
                                }

                                @Override
                                public ListenableFuture<V> reload(final K key, V oldValue) throws Exception {
                                    return BACKGROUND_REFRESH_POOLS.submit(new Callable<V>() {
                                        @Override
                                        public V call() throws Exception {
                                            return getValueWhenExpire(key);
                                        }
                                    });
                                }
                            });
        }
        return cache;
    }


    protected V fetchDataFromCache(K key) throws ExecutionException {
        return getCache().get(key);
    }

    protected void putDataToCache(K key, V value) {
        getCache().put(key, value);
    }
}
