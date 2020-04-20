package com.jiang.config;

/**
 * @ClassName BaseResult
 * @Description BaseResult
 * @Author LIUYQ
 * @Date 2019/10/18 13:50
 * @Version 1.0
 */

public class BaseResult<T> {
    private Integer code;
    private String message;
    private T data;
    private static final Integer SUCCESS_CODE = 0;
    private static final Integer FAIL_CODE = 1;

    public BaseResult(Integer errCode, String message, T data) {
        this.code = errCode;
        this.message = message;
        this.data = data;
    }

    public static BaseResult<Object> success() {
        return new BaseResult(SUCCESS_CODE, (String) null, (Object) null);
    }

    public static BaseResult<Object> success(String message, Object data) {
        return new BaseResult(SUCCESS_CODE, message, data);
    }

    public static BaseResult<Object> success(Integer errCode, String message, Object data) {
        return new BaseResult(errCode, message, data);
    }

    public static BaseResult<Object> success(Object data) {
        return new BaseResult(SUCCESS_CODE, (String) null, data);
    }

    public static BaseResult<Object> fail(String message) {
        return new BaseResult(FAIL_CODE, message, null);
    }

    public static BaseResult<Object> fail(String message, Object data) {
        return new BaseResult(FAIL_CODE, message, data);
    }

    public static BaseResult<Object> fail(Integer failCode, String message, Object data) {
        return new BaseResult(failCode, message, data);
    }

    public static BaseResult<Object> fail(Object data) {
        return new BaseResult(FAIL_CODE, (String) null, data);
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public T getData() {
        return this.data;
    }

    public void setErrCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }
}
