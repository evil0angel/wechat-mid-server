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

    @ExceptionHandler(OperationException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    Object handleOperationException(OperationException ex) {
        ResultMsg resultMsg = new ResultMsg(ResultStatus.OPERATION_ERROR.getCode(),
                ResultStatus.OPERATION_ERROR.getMessage(),
                ex.getMessage());
        return resultMsg;
    }

    @ExceptionHandler(ParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    Object handleParameterException(ParameterException ex) {
        ResultMsg resultMsg = new ResultMsg(ResultStatus.PARAMETER_ERROR.getCode(),
                ResultStatus.PARAMETER_ERROR.getMessage(),
                ex.getMessage());
        return resultMsg;
    }

    @ExceptionHandler(WechatException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public
    @ResponseBody
    Object handleWechatException(WechatException ex) {
        ResultMsg resultMsg = new ResultMsg(ResultStatus.WX_SERVER_ERROR.getCode(),
                ResultStatus.WX_SERVER_ERROR.getMessage(),
                ex.getMessage());
        return resultMsg;
    }
}
