package com.tegasus9.sdk;

import org.springframework.http.HttpMethod;

/**
 * @author XiongYiGe
 * @date 2022/6/1
 * @description 调用第三方接口SDK
 */
public interface ISDKService {

    <R> R sdkSend(ISDKRequest sdkRequest, HttpMethod httpMethod, String url, Class<R> responseType) throws Exception;

}
