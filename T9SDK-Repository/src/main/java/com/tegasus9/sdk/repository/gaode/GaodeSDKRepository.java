package com.tegasus9.sdk.repository.gaode;

import com.tegasus9.sdk.modules.gaode.IGaodeSDKService;
import com.tegasus9.sdk.modules.gaode.data.GaodeGeoCodeRequest;
import com.tegasus9.sdk.modules.gaode.data.GaodeGeoCodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author XiongYiGe
 * @date 2022/6/28
 * @description
 */
@Repository
@Slf4j
public class GaodeSDKRepository {
    @Resource
    private IGaodeSDKService gaodeSDKService;


    /**
     * 调用高德地理编码接口
     * status = 0或 level = 省/市/区县/乡镇 时 无效
     * @param address
     * @return
     */
    public boolean isValidAddress(String address) {
        GaodeGeoCodeRequest request = new GaodeGeoCodeRequest();
        request.setAddress(address);
        GaodeGeoCodeResponse response = gaodeSDKService.getGeoResponse(request);
        String status = response.getStatus();
        if ("0".equals(status)) {
            return false;
        }
        String level = response.getGeoCodesList().get(0).getLevel();
        if("省".equals(level) || "市".equals(level) || "区县".equals(level) || "乡镇".equals(level)){
            return false;
        }
        return true;

    }
}
