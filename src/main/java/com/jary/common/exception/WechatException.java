package com.jary.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by GF on 2017/6/12.
 */
public class WechatException extends Exception {

    private Logger logger = LoggerFactory.getLogger(WechatException.class);
    public WechatException(String message) {
        super(message);
        logger.info("微信异常: " + message);
    }
}
