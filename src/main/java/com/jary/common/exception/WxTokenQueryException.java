package com.jary.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by GF on 2017/6/12.
 */
public class WxTokenQueryException extends Exception {

    private Logger logger = LoggerFactory.getLogger(WxTokenQueryException.class);

    public WxTokenQueryException(String msg) {
        super(msg);
    }

    public WxTokenQueryException() {
        super("没有查询到结果");
    }
}
