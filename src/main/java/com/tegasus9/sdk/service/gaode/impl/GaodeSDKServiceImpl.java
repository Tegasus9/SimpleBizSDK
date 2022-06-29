package com.tegasus9.sdk.service.gaode.impl;

import com.tegasus9.sdk.ISDKRequest;
import com.tegasus9.sdk.ISDKResponse;
import com.tegasus9.sdk.service.gaode.AbstractGaodeSDKService;
import com.tegasus9.sdk.service.gaode.IGaodeSDKService;
import com.tegasus9.sdk.service.gaode.data.GaodeGeoCodeRequest;
import com.tegasus9.sdk.service.gaode.data.GaodeGeoCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

/**
 * @author XiongYiGe
 * @date 2022/6/27
 * @description 高德SDKservice
 */
@Service("gaodeSDKService")
@Slf4j
public class GaodeSDKServiceImpl extends AbstractGaodeSDKService implements IGaodeSDKService {
    /**
     * 调用高德地理编码接口并获取返回结果
     *
     * @param request request
     * @return GaodeGeoCodeResponse
     */
    @Override
    public GaodeGeoCodeResponse getGeoResponse(GaodeGeoCodeRequest request) {
        return sendRequest(request,HttpMethod.GET,super.geoCodeUrl);
    }
    private <Request extends ISDKRequest&ISDKResponse<Resp>,Resp> Resp sendRequest(Request request, HttpMethod  httpMethod, String url) {
        return super.sdkSend(request,httpMethod,url,request.getResponseClass());
    }
}
