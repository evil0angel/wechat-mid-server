package com.jary.common.exception;

import com.jary.common.result.WxError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by GF on 2017/6/12.
 */
public class WxErrorException extends Exception {

    private WxError error;

    private Logger logger = LoggerFactory.getLogger(WxErrorException.class);
    
    public WxErrorException(WxError error) {
        super(error.toString());
        this.error = error;
    }

    public WxError getError() {
        return error;
    }

}
