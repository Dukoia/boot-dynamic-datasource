package com.dukoia.boot.common;

/**
 * @Description: BizException
 * @Author: jiangze.He
 * @Date: 2021-07-21
 * @Version: v1.0
 */
public class BizException extends RuntimeException{

    /**
     * 错误码
     */
    private IRespErrorCode errorCode;

    public BizException(IRespErrorCode errorCode) {
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }


    public BizException(String message) {
        super(message);
    }

    public BizException(Throwable cause) {
        super(cause);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public IRespErrorCode getErrorCode() {
        return errorCode;
    }

}
