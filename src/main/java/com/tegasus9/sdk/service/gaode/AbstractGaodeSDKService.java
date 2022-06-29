package com.tegasus9.sdk.service.gaode;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.tegasus9.sdk.AbstractSDKService;
import com.tegasus9.sdk.ISDKRequest;
import com.tegasus9.sdk.exception.SDKException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/6/29
 * @description
 */
@Slf4j
public abstract class AbstractGaodeSDKService extends AbstractSDKService {
    @Value("${sdk.gaode.key}")
    private String key;
    @Value("${sdk.gaode.url.geoCode}")
    protected String geoCodeUrl;
    @Override
    protected void processHttpHeader(ISDKRequest sdkRequest, HttpHeaders httpHeader) {
        sdkRequest.processHttpHeader(httpHeader);
    }

    @Override
    protected Map<String, ?> processHttpBody(ISDKRequest sdkRequest) throws Exception {
        sdkRequest.encryptHttpBody(null);
        Map<String, String> map = MapUtil.of("key", key);
        sdkRequest.setDefaultProperty(map);
        return sdkRequest.toMap();
    }

    @Override
    protected HttpHeaders getHttpHeader() {
        return new HttpHeaders();
    }

    @Override
    public <R> R sdkSend(ISDKRequest httpBody, HttpMethod httpMethod, String url, Class<R> responseType) throws SDKException {
        String response = null;
        try {
            response = super.sdkSend(httpBody, httpMethod, url, String.class);
        } catch (Exception e) {
            log.error("调用高德接口出现错误",e);
            throw new SDKException(e);
        }
        return JSON.parseObject(response, responseType);
    }
}
