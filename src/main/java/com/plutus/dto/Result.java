package com.plutus.dto;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Result implements Serializable {

    public static final String SUCCESS = "success";
    public static final String FAIL = "FAIL";
    public static final String WARNING = "warning";

    private Integer status;
    private Object data;
    private String message;

    public Result() {}

    public Result(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public Result(Integer status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {

        return status;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
