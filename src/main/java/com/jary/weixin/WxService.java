package com.jary.weixin;

import com.alibaba.fastjson.JSON;
import com.jary.common.exception.WxErrorException;
import com.jary.common.result.WxError;
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
    public static WxAccessToken getAccessToken(String appid, String secret) throws WxErrorException {
        String resultContent = HttpClientUtil.sendHttpGet("https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=client_credential&" +
                "appid=" + appid + "&" +
                "secret=" + secret);
        WxError error = WxError.fromJson(resultContent);
        error.setJson(resultContent);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        }
        return JSON.parseObject(resultContent, WxAccessToken.class);
    }
}
