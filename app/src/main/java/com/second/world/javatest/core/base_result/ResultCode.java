package com.second.world.javatest.core.base_result;

public enum ResultCode {

    OK(1),
    ERROR(-1),
    LOADING(0);

    int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}