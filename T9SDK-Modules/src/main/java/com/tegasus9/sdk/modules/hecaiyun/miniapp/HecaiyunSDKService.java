package com.tegasus9.sdk.modules.hecaiyun.miniapp;

import com.tegasus9.sdk.core.AbstractSDKService;
import com.tegasus9.sdk.core.ISDKRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/6/14
 * @description
 */
@Service
public class HecaiyunSDKService extends AbstractSDKService {

    @Value("${hecaiyun.platform.appId}")
    private String appId;
    @Value("${hecaiyun.platform.secretKey}")
    private String secretKey;
    @Value("${hecaiyun.platform.channelId}")
    private String channelId;
    @Override
    protected void processHttpHeader(ISDKRequest sdkRequest, HttpHeaders httpHeader) {
        sdkRequest.processHttpHeader(httpHeader);
    }

    @Override
    protected Map<String, ?> processHttpBody(ISDKRequest sdkRequest) {
        sdkRequest.encryptHttpBody(secretKey);
        sdkRequest.setDefaultProperty(null);
        return sdkRequest.toMap();

    }

    @Override
    protected HttpHeaders getHttpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("appid", appId);
        httpHeaders.add("secretkey", secretKey);
        httpHeaders.add("channelid", channelId);
        return httpHeaders;
    }
}
