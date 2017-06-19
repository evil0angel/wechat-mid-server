package com.jary.controller;

import com.jary.common.exception.OperationException;
import com.jary.common.exception.ParameterException;
import com.jary.common.exception.WechatException;
import com.jary.model.WxAppParam;
import com.jary.model.WxToken;
import com.jary.service.IWxTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by GF on 2017/6/18.
 */
@RestController
@RequestMapping(value = "/wx-tokens")
public class WxTokenController {

    @Autowired
    private IWxTokenService wxTokenService;

    @RequestMapping(method = RequestMethod.POST)
    public WxToken register(@RequestBody WxAppParam param) throws ParameterException, WechatException {
        return wxTokenService.saveOne(param.getAppid(), param.getSecret());
    }

    @RequestMapping(value = "/{wxId}", method = RequestMethod.GET)
    public WxToken getAccessToken(@PathVariable String wxId){
        return wxTokenService.getById(wxId);
    }

    @RequestMapping(value = "/{wxId}/app", method = RequestMethod.PUT)
    public WxToken updateAppInfo(@PathVariable String wxId, @RequestBody WxAppParam param) throws OperationException, WechatException, ParameterException {
        return wxTokenService.updateAppInfo(wxId, param.getAppid(), param.getSecret());
    }

    @RequestMapping(value = "/{wxId}/token", method = RequestMethod.PUT)
    public WxToken refreshToken(@PathVariable String wxId) throws WechatException, OperationException {
        return wxTokenService.updateToken(wxId);
    }
}
