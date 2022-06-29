package com.tegasus9.sdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/6/1
 * @description 调用第三方接口模板
 */
@Slf4j
public abstract class AbstractSDKService implements ISDKService {
    @Resource
    private RestTemplate restTemplate;
    @Override
    public <R> R sdkSend(ISDKRequest httpBody, HttpMethod httpMethod, String url, Class<R> responseType) throws Exception {
        //1. 获取请求头
        HttpHeaders httpHeader = getHttpHeader();

        //2. 处理请求头
        processHttpHeader(httpBody,httpHeader);

        //3. 处理请求体
        Map<String,?> valueMap = processHttpBody(httpBody);

        //4. 发送请求
        HttpEntity<?> httpEntity = new HttpEntity<>(valueMap,httpHeader);
        log.info("{}发送请求：请求Url:{} 请求方法:{} 请求实体:{}",httpBody.getMethodName(),url,httpMethod,httpEntity);
        ResponseEntity<R> responseEntity = restTemplate.exchange(url, httpMethod,httpEntity , responseType);
        log.info("{}收到返回：responseEntity:{}",httpBody.getMethodName(),responseEntity);
        return responseEntity.getBody();

    }

    protected abstract void processHttpHeader(ISDKRequest sdkRequest, HttpHeaders httpHeader);

    /**
     * 处理请求体
     *
     * @param sdkRequest
     * @return
     */
    protected abstract Map<String,?> processHttpBody(ISDKRequest sdkRequest) throws Exception;

    /**
     * 获取请求头
     * @return
     */
    protected abstract HttpHeaders getHttpHeader();
}
