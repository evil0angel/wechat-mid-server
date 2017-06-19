package com.jary.mapper;

import com.jary.model.WxToken;

import java.util.List;

/**
 * Created by GF on 2017/6/11.
 */
public interface WxTokenMapper extends BaseMapper<WxToken> {
    List<WxToken> getByWxToken(WxToken wxToken);
}
