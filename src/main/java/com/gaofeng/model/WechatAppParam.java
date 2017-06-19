package com.gaofeng.model;

/**
 * Created by gaofeng on 2017/6/18.
 */
public class WechatAppParam {
    // 微信第三方唯一标识
    private String appid;
    // 微信第三方唯一标识密钥
    private String secret;

    public String getAppid() {
        return appid;
    }

    public WechatAppParam setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public WechatAppParam setSecret(String secret) {
        this.secret = secret;
        return this;
    }
}
