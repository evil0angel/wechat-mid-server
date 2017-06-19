package com.gaofeng.common.model;

import java.util.Date;

/**
 * Created by gaofeng on 2017/1/18.
 */
public class ResultMsg {
    private int code;
    private String errMsg;
    private long timestamp;
    private String exception;


    public ResultMsg(int code, String errMsg, String exception) {
        this.timestamp = new Date().getTime();
        this.code = code;
        this.errMsg = errMsg;
        this.exception = exception;
    }

    public int getCode() {
        return code;
    }

    public ResultMsg setCode(int code) {
        this.code = code;
        return this;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public ResultMsg setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ResultMsg setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getException() {
        return exception;
    }

    public ResultMsg setException(String exception) {
        this.exception = exception;
        return this;
    }
}
