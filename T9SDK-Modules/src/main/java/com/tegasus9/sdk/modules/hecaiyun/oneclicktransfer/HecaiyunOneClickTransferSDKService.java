package com.tegasus9.sdk.modules.hecaiyun.oneclicktransfer;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;

import com.tegasus9.sdk.core.AbstractSDKService;
import com.tegasus9.sdk.core.ISDKRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/6/1
 * @description 和彩云一键转存相关SDK
 */
@Service
@Slf4j
public class HecaiyunOneClickTransferSDKService extends AbstractSDKService {

    @Value("${hecaiyun.platform.appId}")
    private String appId;

    @Value("${hecaiyun.platform.secretKey}")
    private String secretKey;

    @Value("${hecaiyun.platform.appKey}")
    private String appKey;

    @Override
    protected void processHttpHeader(ISDKRequest sdkRequest, HttpHeaders httpHeader) {
        sdkRequest.processHttpHeader(httpHeader);
    }

    @Override
    protected Map<String, ?> processHttpBody(ISDKRequest httpBody){
        httpBody.encryptHttpBody(appKey);
        httpBody.setDefaultProperty(null);
        return httpBody.toMap();
    }

    @Override
    protected HttpHeaders getHttpHeader() {
        String nonce = getNonce();
        String timeStamp = getTimeStamp();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("appId",appId);
        httpHeaders.set("nonce", nonce);
        httpHeaders.set("timestamp", timeStamp);
        httpHeaders.set("signature",getSignature(nonce,appId,timeStamp,secretKey));
        return httpHeaders;
    }
    private String getNonce(){
        return RandomStringUtils.randomAlphanumeric(6);
    }
    private String getTimeStamp(){
        return String.valueOf(System.currentTimeMillis() / 1000);
    }
    private String getSignature(String nonce,String appId,String timeStamp,String secretKey){
        Map<String, String> hashmap = new HashMap<>();
        hashmap.put("nonce",nonce);
        hashmap.put("token",secretKey);
        hashmap.put("timeStamp",timeStamp);
        hashmap.put("secretId",appId);
        String joinString = MapUtil.sortJoin(hashmap, "&", "=", true);
        return SecureUtil.sha256(joinString);
    }
}
