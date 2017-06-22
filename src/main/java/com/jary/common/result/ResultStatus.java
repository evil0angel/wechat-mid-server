package com.jary.common.result;

/**
 * 自定义请求状态码
 */
public enum ResultStatus {

    WX_SERVER_ERROR(1001, "微信服务异常"),
    ILLEGAL_ARGUMENT_ERROR(1002, "参数错误"),
    NOT_FOUND_ERROR(1003, "没有查询结果");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
