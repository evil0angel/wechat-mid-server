package com.jary.model;

/**
 * Created by GF on 2017/6/18.
 */
public class WxAppParam {
    // 微信第三方唯一标识
    private String appid;
    // 微信第三方唯一标识密钥
    private String secret;

    public String getAppid() {
        return appid;
    }

    public WxAppParam setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public WxAppParam setSecret(String secret) {
        this.secret = secret;
        return this;
    }
}
