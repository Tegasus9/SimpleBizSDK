package com.tegasus9.sdk.service.gaode;

import com.tegasus9.sdk.service.gaode.data.GaodeGeoCodeRequest;
import com.tegasus9.sdk.service.gaode.data.GaodeGeoCodeResponse;

/**
 * @author XiongYiGe
 * @date 2022/6/29
 * @description
 */
public interface IGaodeSDKService {
    /**
     *  调用高德地理编码接口并获取返回结果
     *  接口地址：<a href="https://restapi.amap.com/v3/geocode/geo?parameters">...</a>
     *  请求方法：GET
     *  @param request request
     *  @return GaodeGeoCodeResponse
     */
    GaodeGeoCodeResponse getGeoResponse(GaodeGeoCodeRequest request);
}
