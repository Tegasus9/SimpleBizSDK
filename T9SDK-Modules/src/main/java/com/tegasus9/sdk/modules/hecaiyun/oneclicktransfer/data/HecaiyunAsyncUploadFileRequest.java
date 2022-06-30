package com.tegasus9.sdk.modules.hecaiyun.oneclicktransfer.data;

import cn.hutool.core.bean.BeanUtil;

import com.tegasus9.sdk.core.ISDKRequest;
import com.tegasus9.sdk.core.exception.SDKException;
import com.tegasus9.sdk.modules.hecaiyun.miniapp.utils.HCYOneClickAesUtil;
import lombok.Data;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/6/2
 * @description
 */
@Data
public class HecaiyunAsyncUploadFileRequest implements ISDKRequest {
    private String businessId;
    private String msisdn;
    private FileSystemResource files;

    @Override
    public void setDefaultProperty(Map<String, String> keyMap) {
        //没有默认值需要设置
    }

    @Override
    public String getMethodName() {

        return "[和彩云异步上传接口]";
    }

    @Override
    public void processHttpHeader(HttpHeaders httpHeaders) {
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
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
        LinkedMultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        Map<String, Object> stringObjectMap = BeanUtil.beanToMap(this,false,true);
        for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
            multiValueMap.add(entry.getKey(),entry.getValue());
        }
        return multiValueMap;
    }


}
