package com.second.world.javatest.data.remote;

public class BaseResult<T> {

    public static final int OK = 1;
    public static final int ERROR = 0;

    private Integer code;
    private String message;
    private T data;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(T data) {
        this.data = data;
    }

    public static <T> BaseResult<T> SUCCESS(T data){
        return new BaseResult<T>(data);
    }

    public static <T> BaseResult<T> ERROR(String message){
        return new BaseResult<T>(BaseResult.ERROR , message);
    }
}
