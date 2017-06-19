package com.jary.common.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by GF on 2017/6/12.
 */
public class ParameterException extends Exception {

    private Logger logger = LoggerFactory.getLogger(ParameterException.class);
    public ParameterException(String message) {
        super(message);
        logger.info("参数错误: " + message);
    }
}
