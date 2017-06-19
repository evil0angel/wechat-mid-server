package com.gaofeng.service.impl;

import com.gaofeng.common.exception.OperationException;
import com.gaofeng.common.exception.ParameterException;
import com.gaofeng.common.exception.WechatException;
import com.gaofeng.mapper.WxTokenMapper;
import com.gaofeng.model.WxToken;
import com.gaofeng.service.IWxTokenService;
import com.gaofeng.utils.BaseUtils;
import com.gaofeng.weixin.AccessToken;
import com.gaofeng.weixin.WechatUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by gaofeng on 2017/6/11.
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
        return wxTokenMapper.getByCondition(wxToken);
    }

    @Override
    public WxToken saveOne(String appid, String secret) throws ParameterException, WechatException {
        if (StringUtils.isBlank(appid) || StringUtils.isBlank(secret)) {
            throw new ParameterException("参数不可以为空");
        }
        AccessToken accessToken = WechatUtil.getAccessToken(appid, secret);
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
        AccessToken accessToken = WechatUtil.getAccessToken(wxToken.getAppid(), wxToken.getSecret());
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
        AccessToken accessToken = WechatUtil.getAccessToken(appid, secret);
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
