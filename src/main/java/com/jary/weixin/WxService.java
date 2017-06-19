package com.jary.weixin;

import com.alibaba.fastjson.JSON;
import com.jary.utils.HttpClientUtil;

/**
 * Created by GF on 2017/6/18.
 */
public class WxService {
    /**
     * 获取AccessToken
     *
     * @param appid
     * @param secret
     * @return
     */
    public static AccessToken getAccessToken(String appid, String secret) {
        String result = HttpClientUtil.sendHttpGet("https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential&" +
                "appid=" + appid + "&" +
                "secret=" + secret);
        return JSON.parseObject(result, AccessToken.class);
    }
}
