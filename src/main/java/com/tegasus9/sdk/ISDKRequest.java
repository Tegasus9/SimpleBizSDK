package com.tegasus9.sdk;

import org.springframework.http.HttpHeaders;

import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/6/1
 * @description SDK调用请求
 */
public interface ISDKRequest {

    /**
     * 设置默认属性
     */
    void setDefaultProperty(Map<String, String> keyMap);

    /**
     * 获取方法名
     * @return methodName
     */
    String getMethodName();

    /**
     * 处理请求头
     * 一般在request中独特处理请求用于赋予不同contentType
     * @param httpHeaders
     */
    void processHttpHeader(HttpHeaders httpHeaders);

    /**
     * 加密请求体中相关参数
     * 一般用来加密手机号
     * @param encryptKey
     * @throws Exception
     */
    void encryptHttpBody(String encryptKey) throws Exception;

    /**
     * 将此request转换成map
     * @return parametersMap
     */
    Map<String,?> toMap();

}
