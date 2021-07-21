package com.dukoia.boot.common;

import com.dukoia.boot.enums.ReturnCode;
import lombok.Data;

/**
 * @Description: Result
 * @Author: jiangze.He
 * @Date: 2021-07-20
 * @Version: v1.0
 */
@Data
public class Result<T> {

    private int status;
    private String message;
    private T data;
    private long timestamp ;

    public Result(){
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> Result<T> success(T data) {
        Result<T> resultData = new Result<>();
        resultData.setStatus(ReturnCode.RC100.getCode());
        resultData.setMessage(ReturnCode.RC100.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> Result<T> fail(int code, String message) {
        Result<T> resultData = new Result<>();
        resultData.setStatus(code);
        resultData.setMessage(message);
        return resultData;
    }

    public static <T> Result<T> fail(ReturnCode returnCode) {
        Result<T> resultData = new Result<>();
        resultData.setStatus(returnCode.getCode());
        resultData.setMessage(returnCode.getMessage());
        return resultData;
    }

    public Boolean success(){
        return ReturnCode.RC100.getCode() == this.status;
    }
}
