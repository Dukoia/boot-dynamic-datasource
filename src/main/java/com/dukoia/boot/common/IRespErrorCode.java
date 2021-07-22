package com.dukoia.boot.common;

/**
 * @author fanshk
 * @date 2019/11/21 10:43
 */
public interface IRespErrorCode {

    /**
     * 返回状态 0、失败 1、成功
     */
    int getStatus();

    /**
     * 配置信息属性名,eg: api.response.code.paramNull
     */
    String getMsg();

    /**
     * 失败信息
     *
     * @return
     */
    String getExt();
}
