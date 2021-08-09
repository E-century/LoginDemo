package com.mydemo.network.config;



public class ApiResult<T> {
    public boolean success;
    public String msg;
    public T data;

    public String getMessage() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

}
