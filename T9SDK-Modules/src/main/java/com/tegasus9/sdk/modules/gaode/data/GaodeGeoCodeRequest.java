package com.tegasus9.sdk.modules.gaode.data;

import cn.hutool.core.bean.BeanUtil;
import com.tegasus9.sdk.core.ISDKRequest;
import com.tegasus9.sdk.core.ISDKResponse;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/6/27
 * @description 高德地理编码请求
 */
@Data
public class GaodeGeoCodeRequest implements ISDKRequest, ISDKResponse<GaodeGeoCodeResponse> {
    private String key;
    private String address;
    private String city;
    private String batch;
    private String sig;
    private String output;
    private String callback;

    @Override
    public void setDefaultProperty(Map<String, String> keyMap) {
        this.setKey(keyMap.get("key"));
    }

    @Override
    public String getMethodName() {
        return "[高德地图地理编码接口]";
    }

    @Override
    public void processHttpHeader(HttpHeaders httpHeaders) {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    /**
     * 此处只用来设置key存放。
     * @param encryptKey
     */
    @Override
    public void encryptHttpBody(String encryptKey) {
        this.setKey(encryptKey);
    }

    @Override
    public Map<String, ?> toMap() {
        return BeanUtil.beanToMap(this, false,true);
    }

    @Override
    public Class<GaodeGeoCodeResponse> getResponseClass() {
        return GaodeGeoCodeResponse.class;
    }
}
