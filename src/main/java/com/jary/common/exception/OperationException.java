package com.jary.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by GF on 2017/6/12.
 */
public class OperationException extends Exception {

    private Logger logger = LoggerFactory.getLogger(OperationException.class);
    public OperationException(String message) {
        super(message);
        logger.info("操作异常: " + message);
    }
}
