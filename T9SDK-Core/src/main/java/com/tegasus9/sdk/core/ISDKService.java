package com.tegasus9.sdk.core;

import com.tegasus9.sdk.core.exception.SDKException;
import org.springframework.http.HttpMethod;

/**
 * @author XiongYiGe
 * @date 2022/6/1
 * @description 调用第三方接口SDK
 */
public interface ISDKService {

    <R> R sdkSend(ISDKRequest sdkRequest, HttpMethod httpMethod, String url, Class<R> responseType) throws SDKException;

}
