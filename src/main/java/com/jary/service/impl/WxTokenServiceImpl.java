package com.jary.service.impl;

import com.jary.common.exception.WxErrorException;
import com.jary.common.exception.WxTokenQueryException;
import com.jary.mapper.WxTokenMapper;
import com.jary.model.WxToken;
import com.jary.service.IWxTokenService;
import com.jary.utils.BaseUtils;
import com.jary.weixin.WxAccessToken;
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
    public WxToken saveOne(String appid, String secret) throws IllegalArgumentException, WxErrorException {
        if (StringUtils.isBlank(appid) || StringUtils.isBlank(secret)) {
            throw new IllegalArgumentException("appid&secret不可以为空");
        }
        WxAccessToken wxAccessToken = WxService.getAccessToken(appid, secret);
        WxToken wxToken = new WxToken();
        wxToken
                .setWxId(UUID.randomUUID().toString().replaceAll("-", ""))
                .setAppid(appid)
                .setSecret(secret)
                .setAccessToken(wxAccessToken.getAccessToken())
                .setExpiresIn(new DateTime(new Date()).plusSeconds(wxAccessToken.getExpiresIn()).toDate())
                .setRefreshIn(new Date())
                .setRefreshTimes(1)
                .setStatus(STATUS_NORMAL);
        wxTokenMapper.insert(wxToken);
        return wxToken;
    }

    @Override
    public WxToken updateToken(String wxId) throws WxErrorException, WxTokenQueryException {
        WxToken wxToken = wxTokenMapper.getOne(wxId);
        if(wxToken == null){
            throw new WxTokenQueryException("没有找到当前的WxToken");
        }
        if (wxToken.getStatus().equals(STATUS_MODIFYING)) {
            return wxToken;
        }
        modifyStatus(wxId, STATUS_MODIFYING);
        WxAccessToken wxAccessToken;
        try {
            wxAccessToken = WxService.getAccessToken(wxToken.getAppid(), wxToken.getSecret());
        } catch (WxErrorException e) {
            modifyStatus(wxId, STATUS_NORMAL);
            throw new WxErrorException(e.getError());
        }
        if (BaseUtils.isSameDate(wxToken.getRefreshIn(), new Date())) {
            wxToken.setRefreshTimes(wxToken.getRefreshTimes() + 1);
        } else {
            wxToken.setRefreshTimes(1);
        }
        wxToken
                .setAccessToken(wxAccessToken.getAccessToken())
                .setExpiresIn(new DateTime(new Date()).plusSeconds(wxAccessToken.getExpiresIn()).toDate())
                .setRefreshIn(new Date())
                .setStatus(STATUS_NORMAL);
        wxTokenMapper.update(wxToken);
        return wxToken;
    }

    @Override
    public WxToken updateAppInfo(String wxId, String appid, String secret) throws WxErrorException {
        if (StringUtils.isBlank(appid) || StringUtils.isBlank(secret)) {
            throw new IllegalArgumentException("appid&secret不可以为空");
        }
        WxToken wxToken = wxTokenMapper.getOne(wxId);
        if (wxToken.getStatus().equals(STATUS_MODIFYING)) {
            return wxToken;
        }
        modifyStatus(wxId, STATUS_MODIFYING);
        WxAccessToken wxAccessToken;
        try {
            wxAccessToken = WxService.getAccessToken(wxToken.getAppid(), wxToken.getSecret());
        } catch (WxErrorException e) {
            modifyStatus(wxId, STATUS_NORMAL);
            throw new WxErrorException(e.getError());
        }
        wxToken
                .setAppid(appid)
                .setSecret(secret)
                .setAccessToken(wxAccessToken.getAccessToken())
                .setExpiresIn(new DateTime(new Date()).plusSeconds(wxAccessToken.getExpiresIn()).toDate())
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
