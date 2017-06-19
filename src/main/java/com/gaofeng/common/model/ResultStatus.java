package com.gaofeng.common.model;

/**
 * 自定义请求状态码
 */
public enum ResultStatus {

    /**
     */
    OPERATION_ERROR(1004, "操作异常"),
    PARAMETER_ERROR(1005, "参数错误"),
    WX_SERVER_ERROR(1006, "微信服务异常");


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
