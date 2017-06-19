package com.gaofeng.model;

import java.util.Date;

/**
 * Created by gaofeng on 2017/6/11.
 */
public class WxToken {
    // 微信公众号唯一标识
    private String wxId;
    // 微信第三方唯一标识
    private String appid;
    // 微信第三方唯一标识密钥
    private String secret;
    // access_token
    private String accessToken;
    // 过期时间
    private Date expiresIn;
    // 最后刷新时间
    private Date refreshIn;
    // 当天刷新次数
    private Integer refreshTimes;
    // 状态(1正常|2修改中)
    private String status;

    public String getWxId() {
        return wxId;
    }

    public WxToken setWxId(String wxId) {
        this.wxId = wxId;
        return this;
    }

    public String getAppid() {
        return appid;
    }

    public WxToken setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public WxToken setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public WxToken setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public Date getExpiresIn() {
        return expiresIn;
    }

    public WxToken setExpiresIn(Date expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public Date getRefreshIn() {
        return refreshIn;
    }

    public WxToken setRefreshIn(Date refreshIn) {
        this.refreshIn = refreshIn;
        return this;
    }

    public Integer getRefreshTimes() {
        return refreshTimes;
    }

    public WxToken setRefreshTimes(Integer refreshTimes) {
        this.refreshTimes = refreshTimes;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public WxToken setStatus(String status) {
        this.status = status;
        return this;
    }
}
