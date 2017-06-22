package com.jary.common;

import com.jary.common.exception.WxErrorException;
import com.jary.common.exception.WxTokenQueryException;
import com.jary.model.WxToken;
import com.jary.service.IWxTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by GF on 2017/6/18.
 */
public class RefreshThread implements Runnable {

    private Logger logger = LoggerFactory.getLogger(RefreshThread.class);

    private WxToken wxToken;
    private IWxTokenService wxTokenService;

    public RefreshThread(IWxTokenService wxTokenService, WxToken wxToken) {
        this.wxTokenService = wxTokenService;
        this.wxToken = wxToken;
    }

    public void run() {
        logger.info(Thread.currentThread() + "开始请求 wx_id:" + wxToken.getWxId());
        try {
            wxTokenService.updateToken(wxToken.getWxId());
        } catch (WxErrorException e) {
            logger.error(Thread.currentThread() + " wechat:" + e.getMessage());
            e.printStackTrace();
        } catch (WxTokenQueryException e) {
            logger.error(Thread.currentThread() + " mid:" + e.getMessage());
            e.printStackTrace();
        }
        logger.info(wxToken.getWxId() + "刷新成功" + wxToken.getAccessToken());
    }
}
