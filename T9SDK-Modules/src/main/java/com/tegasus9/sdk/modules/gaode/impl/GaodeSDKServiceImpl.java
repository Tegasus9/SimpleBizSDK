package com.tegasus9.sdk.modules.gaode.impl;

import com.tegasus9.sdk.modules.gaode.AbstractGaodeSDKService;
import com.tegasus9.sdk.modules.gaode.IGaodeSDKService;
import com.tegasus9.sdk.modules.gaode.data.GaodeGeoCodeRequest;
import com.tegasus9.sdk.modules.gaode.data.GaodeGeoCodeResponse;
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
        return super.sdkSend(request,HttpMethod.GET,geoCodeUrl,GaodeGeoCodeResponse.class);
    }

}
