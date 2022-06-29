package com.tegasus9.sdk.modules.gaode.data;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author XiongYiGe
 * @date 2022/6/27
 * @description
 */
@Data
public class GaodeGeoCodeResponse {
    private String status;
    private String count;
    private String info;
    @JSONField(name = "geocodes")
    private List<GeoCodes> geoCodesList;
    @Data
    public static class GeoCodes {
        private String country;
        private String province;
        private String city;
        private String cityCode;
        private String district;
        private String street;
        private String number;
        private String adcode;
        private String location;
        private String level;
    }
}
