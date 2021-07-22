package com.dukoia.boot.common;

import lombok.Getter;
import lombok.ToString;

/**
 * @author fanshk
 * @date 2019/12/3 10:29
 */
@Getter
@ToString
public enum ResponseCodeI18n implements IRespErrorCode {
    /**
     * 返回状态
     */
    FAILED(0, "api.response.code.fail", "操作失败", "failed"),
    SUCCESS(1, "api.response.code.success", "执行成功", "success");

    private final int status;
    /**
     * 配置信息属性名,eg: api.response.code.paramNull
     */
    private final String msg;
    private final String ext;

    ResponseCodeI18n(int status, String message, String msg, String ext) {
        this.status = status;
        this.msg = message;
        this.ext = ext;
    }
}
