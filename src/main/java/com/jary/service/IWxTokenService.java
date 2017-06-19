package com.jary.service;

import com.jary.common.exception.OperationException;
import com.jary.common.exception.ParameterException;
import com.jary.common.exception.WechatException;
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
     * @throws ParameterException
     * @throws WechatException
     */
    WxToken saveOne(String appid, String secret) throws ParameterException, WechatException;

    /**
     * 更新公众号AccessToken
     *
     * @param wxId
     * @return
     */
    WxToken updateToken(String wxId) throws WechatException, OperationException;

    /**
     * 更新公众号信息
     *
     * @param wxId
     * @param appid
     * @param secret
     * @return
     * @throws ParameterException
     * @throws WechatException
     */
    WxToken updateAppInfo(String wxId, String appid, String secret) throws ParameterException, WechatException, OperationException;

}
