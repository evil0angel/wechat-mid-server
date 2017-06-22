package com.jary.weixin;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by GF on 2017/6/18.
 */
public class WxAccessToken implements Serializable {

    private static final long serialVersionUID = 8401201822364635155L;

    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expires_in")
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public WxAccessToken setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public WxAccessToken setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }
}
