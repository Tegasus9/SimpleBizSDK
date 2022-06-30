package com.tegasus9.sdk.modules.hecaiyun.oneclicktransfer.data;

import cn.hutool.core.bean.BeanUtil;

import com.tegasus9.sdk.core.ISDKRequest;
import com.tegasus9.sdk.core.exception.SDKException;
import com.tegasus9.sdk.modules.hecaiyun.miniapp.utils.HCYOneClickAesUtil;
import lombok.Data;
import org.springframework.http.HttpHeaders;

import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/6/6
 * @description
 */
@Data
public class HecaiyunGetTransferStatusRequest implements ISDKRequest {
    private String msisdn;

    @Override
    public void setDefaultProperty(Map<String, String> keyMap) {
        //no
    }

    @Override
    public String getMethodName() {
        return "[和彩云查询转存结果接口]";
    }

    @Override
    public void processHttpHeader(HttpHeaders httpHeaders) {
        //no
    }

    @Override
    public void encryptHttpBody(String encryptKey){
        try {
            msisdn = HCYOneClickAesUtil.encryptMsisdn(msisdn,encryptKey);
        } catch (Exception e) {
          throw new SDKException("加密手机号失败",e);
        }
    }

    @Override
    public Map<String, ?> toMap() {
        return BeanUtil.beanToMap(this,false,false);
    }
}
