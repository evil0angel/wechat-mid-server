package com.gaofeng.common;

import com.gaofeng.common.exception.OperationException;
import com.gaofeng.common.exception.WechatException;
import com.gaofeng.model.WxToken;
import com.gaofeng.service.IWxTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gaofeng on 2017/6/18.
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
        logger.info(Thread.currentThread() + " wx_id:" + wxToken.getWxId());
        try {
            wxTokenService.updateToken(wxToken.getWxId());
        } catch (WechatException e) {
            logger.error(Thread.currentThread() + " wechat:" + e.getMessage());
            e.printStackTrace();
        } catch (OperationException e) {
            logger.error(Thread.currentThread() + " operation:" + e.getMessage());
        }
        logger.info(wxToken.getWxId() + "刷新成功" + wxToken.getAccessToken());
    }
}
