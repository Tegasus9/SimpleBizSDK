package com.tegasus9.sdk.modules.hecaiyun.oneclicktransfer.data;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.tegasus9.sdk.core.ISDKRequest;
import com.tegasus9.sdk.core.exception.SDKException;
import com.tegasus9.sdk.modules.hecaiyun.miniapp.utils.HCYOneClickAesUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/6/1
 * @description
 */
@Data
public class HecaiyunOneClickTransferJsonBodyRequest implements ISDKRequest {
    /**
     * 上传文件实体列表（当uploadMode为1时必填）
     */
    private List<HecaiyunUploadFile> files;
    /**
     * 加密手机号（AES加密）（必填）
     */
    private String msisdn;
    /**
     * sim快捷弹窗显示内容（传入应用/业务名称，十个字以内）（authMode为1时必填）
     */
    private String content;
    /**
     * 是否帮用户开通彩云业务能力（选填，0为不开通，1为开通。默认不帮用户开通）
     */
    private Integer ifOpenAccount;
    /**
     *  是否返回用户的scheme链接（选填，0为不返回，1为返回。默认不返回）
     */
    private Integer ifScheme;
    /**
     * 上传文件类型（必填）
     * 1,"我的卡包/账单/话费账单"
     * 2,"我的卡包/账单/积分账单"
     * 3,"我的卡包/账单/流量账单"
     * 4,"我的卡包/账单/趣味账单"
     * 5,"我的卡包/发票/话费发票"
     * 6,"我的卡包/协议/移动套餐协议"
     * 7,"我的卡包/账单/其他账单"
     * 8,"我的卡包/发票/购物发票"
     * 9,"我的卡包/发票/交通发票"
     * 10,"我的卡包/发票/餐饮发票"
     * 11,"我的卡包/发票/其他发票"
     * 12,"我的卡包/协议/其他协议"
     */
    private Integer fileType;
    /**
     * 业务主键（调用方唯一识别当前转存任务的业务标识）
     */
    private String businessId;
    /**
     * 上传模式（必填，不填默认为1）
     * 1,同步上传文件（本次请求携带文件）
     * 2，异步上传文件（调用接口2异步上传文件）
     */
    private Integer uploadMode;
    /**
     * 选择短验方式时返回的短验访问链接类型
     * （当authMode选择2时必填，不填默认公网）
     * 1为公网链接，
     * 2为承载网链接，
     * 3为MISWAN承载网链接
     */
    private Integer urlType;
    /**
     * 授权方式（必填，不填默认为2）
     * 1、SIM快捷
     * 2、短信验证码
     * 3、统一认证Token单点登录
     * 4、ip免认证授权（该方式需要业务方提前将出访ip告知给一键转存，该方式目前无异步）
     * 5、短信授权方式
     */
    private Integer authMode;
    /**
     * 统一认证单点登录token（当authMode选择为3时必填）
     */
    private String sessionId;

    @Override
    public void setDefaultProperty(Map<String, String> keyMap) {
        this.authMode = 4;
        this.fileType = 12;
        this.ifOpenAccount = 1;
        this.businessId = UUID.fastUUID().toString(true);
    }

    @Override
    public String getMethodName() {
        return "【一键转存(JSON)接口】";
    }

    @Override
    public void processHttpHeader(HttpHeaders httpHeaders) {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Override
    public void encryptHttpBody(String encryptKey) {
        try {
            msisdn = HCYOneClickAesUtil.encryptMsisdn(msisdn,encryptKey);
        } catch (Exception e) {
            throw new SDKException("加密手机号失败",e);
        }
    }

    @Override
    public Map<String, ?> toMap() {
        return BeanUtil.beanToMap(this,false,true);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HecaiyunUploadFile {
        /**
         * 文件名，带后缀
         */
        private String name;
        /**
         * 文件内容base64编码字符串
         */
        private String content;

        @Override
        public String toString() {
            return "HecaiyunUploadFile{" +
                    "name='" + name + '\'' +
                    ", contentLength='" + (content==null?null:content.length()) + '\'' +
                    '}';
        }
    }
}
