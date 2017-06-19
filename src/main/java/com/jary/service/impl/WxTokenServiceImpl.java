package com.jary.service.impl;

import com.jary.common.exception.OperationException;
import com.jary.common.exception.ParameterException;
import com.jary.common.exception.WechatException;
import com.jary.mapper.WxTokenMapper;
import com.jary.model.WxToken;
import com.jary.service.IWxTokenService;
import com.jary.utils.BaseUtils;
import com.jary.weixin.AccessToken;
import com.jary.weixin.WxService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by GF on 2017/6/11.
 */
@Service
public class WxTokenServiceImpl implements IWxTokenService {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private WxTokenMapper wxTokenMapper;

    @Override
    public WxToken getById(String wxId) {
        return wxTokenMapper.getOne(wxId);
    }

    @Override
    public List<WxToken> getAllByStatus(String status) {
        WxToken wxToken = new WxToken();
        wxToken.setStatus(status);
        return wxTokenMapper.getByWxToken(wxToken);
    }

    @Override
    public WxToken saveOne(String appid, String secret) throws ParameterException, WechatException {
        if (StringUtils.isBlank(appid) || StringUtils.isBlank(secret)) {
            throw new ParameterException("参数不可以为空");
        }
        AccessToken accessToken = WxService.getAccessToken(appid, secret);
        if (accessToken == null) {
            throw new WechatException("获取AccessToken失败");
        }
        WxToken wxToken = new WxToken();
        wxToken
                .setWxId(UUID.randomUUID().toString())
                .setAppid(appid)
                .setSecret(secret)
                .setAccessToken(accessToken.getAccessToken())
                .setExpiresIn(new DateTime(new Date()).plusSeconds(accessToken.getExpiresIn()).toDate())
                .setRefreshIn(new Date())
                .setRefreshTimes(1)
                .setStatus(STATUS_NORMAL);
        wxTokenMapper.insert(wxToken);
        return wxToken;
    }

    @Override
    public WxToken updateToken(String wxId) throws WechatException, OperationException {
        WxToken wxToken = wxTokenMapper.getOne(wxId);
        if (wxToken.getStatus().equals(STATUS_MODIFYING)) {
            throw new OperationException("Token已处于修改中");
        }
        modifyStatus(wxId, STATUS_MODIFYING);
        AccessToken accessToken = WxService.getAccessToken(wxToken.getAppid(), wxToken.getSecret());
        if (accessToken == null) {
            modifyStatus(wxId, STATUS_NORMAL);
            throw new WechatException("获取AccessToken失败");
        }
        if(BaseUtils.isSameDate(wxToken.getRefreshIn(), new Date())){
            wxToken.setRefreshTimes(wxToken.getRefreshTimes() + 1);
        }else{
            wxToken.setRefreshTimes(1);
        }
        wxToken
                .setAccessToken(accessToken.getAccessToken())
                .setExpiresIn(new DateTime(new Date()).plusSeconds(accessToken.getExpiresIn()).toDate())
                .setRefreshIn(new Date())
                .setStatus(STATUS_NORMAL);
        wxTokenMapper.update(wxToken);
        return wxToken;
    }

    @Override
    public WxToken updateAppInfo(String wxId, String appid, String secret) throws ParameterException, WechatException, OperationException {
        if (StringUtils.isBlank(appid) || StringUtils.isBlank(secret)) {
            throw new ParameterException("参数不可以为空");
        }
        WxToken wxToken = wxTokenMapper.getOne(wxId);
        if (wxToken.getStatus().equals(STATUS_MODIFYING)) {
            throw new OperationException("Token正处于修改中");
        }
        modifyStatus(wxId, STATUS_MODIFYING);
        AccessToken accessToken = WxService.getAccessToken(appid, secret);
        if (accessToken == null) {
            modifyStatus(wxId, STATUS_NORMAL);
            throw new WechatException("获取AccessToken失败");
        }
        wxToken
                .setAppid(appid)
                .setSecret(secret)
                .setAccessToken(accessToken.getAccessToken())
                .setExpiresIn(new DateTime(new Date()).plusSeconds(accessToken.getExpiresIn()).toDate())
                .setRefreshIn(new Date())
                .setRefreshTimes(1)
                .setStatus(STATUS_NORMAL);
        wxTokenMapper.update(wxToken);
        return wxToken;
    }

    /**
     * 修改状态
     *
     * @param wxId
     * @param status
     */
    private void modifyStatus(String wxId, String status) {
        WxToken wxToken = new WxToken();
        wxToken
                .setWxId(wxId)
                .setStatus(status);
        wxTokenMapper.update(wxToken);
    }
}
