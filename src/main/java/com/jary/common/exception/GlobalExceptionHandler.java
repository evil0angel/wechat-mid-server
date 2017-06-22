package com.jary.common.exception;

import com.jary.common.result.ResultMsg;
import com.jary.common.result.ResultStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by GF on 2017/6/18.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WxErrorException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public
    @ResponseBody
    Object handleWxErrorException(WxErrorException ex) {
        ResultMsg resultMsg = new ResultMsg(ResultStatus.WX_SERVER_ERROR.getCode(),
                ResultStatus.WX_SERVER_ERROR.getMessage(),
                ex.getError().getJson());
        return resultMsg;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public
    @ResponseBody
    Object handleIllegalArgumentException(WxErrorException ex) {
        ResultMsg resultMsg = new ResultMsg(ResultStatus.ILLEGAL_ARGUMENT_ERROR.getCode(),
                ResultStatus.ILLEGAL_ARGUMENT_ERROR.getMessage(),
                ex.getMessage());
        return resultMsg;
    }

    @ExceptionHandler(WxTokenQueryException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public
    @ResponseBody
    Object handleWxTokenQueryException(WxTokenQueryException ex) {
        ResultMsg resultMsg = new ResultMsg(ResultStatus.NOT_FOUND_ERROR.getCode(),
                ResultStatus.NOT_FOUND_ERROR.getMessage(),
                ex.getMessage());
        return resultMsg;
    }
}
