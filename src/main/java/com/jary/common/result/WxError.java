package com.jary.common.result;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 微信错误码说明
 * http://mp.weixin.qq.com/wiki/index.php?title=全局返回码说明
 *
 * @author Daniel Qian
 */
public class WxError implements Serializable {
    @JSONField(name = "errcode")
    private int errorCode;

    @JSONField(name = "errmsg")
    private String errorMsg;

    private String json;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public static WxError fromJson(String json) {
        WxError error = JSON.parseObject(json, WxError.class);
        return error;
    }

    @Override
    public String toString() {
        return "微信错误: errcode=" + errorCode + ", errmsg=" + errorMsg + "\njson:" + json;
    }

}
