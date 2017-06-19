package com.gaofeng.controller;

import com.gaofeng.common.exception.OperationException;
import com.gaofeng.common.exception.ParameterException;
import com.gaofeng.common.exception.WechatException;
import com.gaofeng.model.RegisterParam;
import com.gaofeng.model.WechatAppParam;
import com.gaofeng.model.WxToken;
import com.gaofeng.service.IWxTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gaofeng on 2017/6/18.
 */
@RestController
@RequestMapping(value = "/wx-tokens")
public class WxTokenController {

    @Autowired
    private IWxTokenService wxTokenService;

    @RequestMapping(method = RequestMethod.POST)
    public WxToken register(@RequestBody RegisterParam param) throws ParameterException, WechatException {
        return wxTokenService.saveOne(param.getAppid(), param.getSecret());
    }

    @RequestMapping(value = "/{wxId}", method = RequestMethod.GET)
    public WxToken getAccessToken(@PathVariable String wxId){
        return wxTokenService.getById(wxId);
    }

    @RequestMapping(value = "/{wxId}/app", method = RequestMethod.PUT)
    public WxToken updateAppInfo(@PathVariable String wxId, @RequestBody WechatAppParam param) throws OperationException, WechatException, ParameterException {
        return wxTokenService.updateAppInfo(wxId, param.getAppid(), param.getSecret());
    }

    @RequestMapping(value = "/{wxId}/token", method = RequestMethod.PUT)
    public WxToken refreshToken(@PathVariable String wxId) throws WechatException, OperationException {
        return wxTokenService.updateToken(wxId);
    }
}
