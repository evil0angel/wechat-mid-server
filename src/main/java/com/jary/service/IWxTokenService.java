package com.jary.service;

import com.jary.common.exception.WxErrorException;
import com.jary.common.exception.WxTokenQueryException;
import com.jary.model.WxToken;

import java.util.List;

/**
 * Created by GF on 2017/6/11.
 */

public interface IWxTokenService {
    String STATUS_NORMAL = "1";
    String STATUS_MODIFYING = "2";

    /**
     * 根据ID获取
     *
     * @param wxId
     * @return
     */
    WxToken getById(String wxId);

    /**
     * 根据状态获取（""全部）
     *
     * @param status
     * @return
     */
    List<WxToken> getAllByStatus(String status);

    /**
     * 新录入一个公众号
     *
     * @param appid
     * @param secret
     * @return
     * @throws IllegalArgumentException
     * @throws WxErrorException
     */
    WxToken saveOne(String appid, String secret) throws IllegalArgumentException, WxErrorException;

    /**
     * 更新公众号AccessToken
     *
     * @param wxId
     * @return
     */
    WxToken updateToken(String wxId) throws WxErrorException, WxTokenQueryException;

    /**
     * 更新公众号信息
     *
     * @param wxId
     * @param appid
     * @param secret
     * @return
     * @throws IllegalArgumentException
     * @throws WxErrorException
     */
    WxToken updateAppInfo(String wxId, String appid, String secret) throws IllegalArgumentException, WxErrorException;

}
