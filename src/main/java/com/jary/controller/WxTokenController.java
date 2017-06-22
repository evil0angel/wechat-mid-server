package com.jary.controller;

import com.jary.common.exception.WxErrorException;
import com.jary.common.exception.WxTokenQueryException;
import com.jary.model.WxAppParam;
import com.jary.model.WxToken;
import com.jary.service.IWxTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by GF on 2017/6/18.
 */
@RestController
@RequestMapping(value = "/wxtokens")
public class WxTokenController {

    @Autowired
    private IWxTokenService wxTokenService;

    @RequestMapping(method = RequestMethod.POST)
    public WxToken register(@RequestBody WxAppParam param) throws IllegalArgumentException, WxErrorException {
        return wxTokenService.saveOne(param.getAppid(), param.getSecret());
    }

    @RequestMapping(value = "/{wxId}", method = RequestMethod.GET)
    public WxToken getAccessToken(@PathVariable String wxId) {
        return wxTokenService.getById(wxId);
    }

    @RequestMapping(value = "/{wxId}", method = RequestMethod.PUT)
    public WxToken updateAppInfo(@PathVariable String wxId, @RequestBody WxAppParam param) throws IllegalArgumentException, WxErrorException {
        return wxTokenService.updateAppInfo(wxId, param.getAppid(), param.getSecret());
    }

    @RequestMapping(value = "/{wxId}/refresh", method = RequestMethod.GET)
    public WxToken refreshToken(@PathVariable String wxId) throws WxErrorException, WxTokenQueryException {
        return wxTokenService.updateToken(wxId);
    }
}
