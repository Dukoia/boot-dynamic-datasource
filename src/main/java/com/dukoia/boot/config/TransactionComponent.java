package com.dukoia.boot.config;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;

/**
 * @Description: TransactionComponent
 * @Author: jiangze.He
 * @Date: 2021-08-30
 * @Version: v1.0
 */
@Component
public class TransactionComponent {

    /**
     *
     * @param <T>
     */
    public interface Callback<T>{

        /**
         * 有返回调用
         * @return
         * @throws Exception
         */
        T run() throws Exception;
    }

    /**
     * 调用无返回值
     */
    public interface CallbackWithOutResult {
        /**
         * 无返回值调用
         * @throws Exception
         */
        void run() throws Exception;
    }

    /**
     * 有返回值的调用
     * @param callback
     * @param <T>
     * @return
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Nullable
    public <T> T doTransactional(Callback<T> callback) throws Exception {
        return callback.run();
    }

    /**
     * 无返回值调用
     * @param callbackWithOutResult
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Nullable
    public void doTransactionalWithOutResult(CallbackWithOutResult callbackWithOutResult) throws Exception {
        callbackWithOutResult.run();
    }
}
