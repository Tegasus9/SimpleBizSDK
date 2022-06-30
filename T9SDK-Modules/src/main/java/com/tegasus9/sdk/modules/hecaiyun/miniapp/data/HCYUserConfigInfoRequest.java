package com.tegasus9.sdk.modules.hecaiyun.miniapp.data;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.tegasus9.sdk.core.ISDKRequest;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/6/14
 * @description 和彩云用户信息查询
 * 用以查询用户是否点击了自动备份开关，
 * 可以区分本月（自然月）的备份开关状态；
 */
@Data
public class HCYUserConfigInfoRequest implements ISDKRequest {

    private static final String WEIXIN = "weixin";

    private String phone;
    private String type;

    @Override
    public void setDefaultProperty(Map<String, String> keyMap) {
        if (StringUtils.isBlank(type)) {
            this.setType(WEIXIN);
        }
    }

    @Override
    public String getMethodName() {
        return "【用户设置信息查询】";
    }

    @Override
    public void processHttpHeader(HttpHeaders httpHeaders) {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void encryptHttpBody(String encryptKey) {
        this.phone = SecureUtil.aes(SecureUtil.md5(encryptKey).getBytes()).encryptBase64(this.phone).toLowerCase();
    }

    @Override
    public Map<String, ?> toMap() {
        return BeanUtil.beanToMap(this, false, true);
    }
}
