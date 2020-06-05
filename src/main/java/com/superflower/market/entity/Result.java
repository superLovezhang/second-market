package com.superflower.market.entity;

import java.io.Serializable;

public class Result implements Serializable {

    private long code;
    private String message;
    private Object data;

    public static Result success(String message, Object data) {
        return new Result(2000l, message, data);
    }

    public static Result successWithoutData(String message) {
        return new Result(2000l, message, null);
    }

    public static Result fail(long code, String message) {
        return new Result(code, message, null);
    }


    public Result(long code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
