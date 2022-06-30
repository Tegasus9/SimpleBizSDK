package com.tegasus9.sdk.modules.hecaiyun.miniapp.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @ClassName AESEncryptUtil
 * @Description AES加密工具类
 * @Author Victor Yu
 * @Date 2021/4/14 16:40
 **/
public class HCYOneClickAesUtil {
    private HCYOneClickAesUtil(){

    }

    /**
     * base 64 encode
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     */
    public static String base64Encode(byte[] bytes){
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] getAESKey(String encodingAESKey){
        return Base64.decodeBase64(encodingAESKey+"=");
    }

    /**防止在linux下随机生成key
     * @throws NoSuchAlgorithmException */
    public static void keyInit(KeyGenerator keyGenerator, byte[] bytes)
            throws NoSuchAlgorithmException {
        //1.防止linux下 随机生成key
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
        secureRandom.setSeed(bytes);
        //2.根据密钥初始化密钥生成器
        keyGenerator.init(128, secureRandom);
    }

    public static byte[] aesEncryptToBytes(String content, byte[] encryptKey, String cipherParam) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        keyInit(kgen, encryptKey);

        Cipher cipher = Cipher.getInstance(cipherParam);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

        return cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
    }

    public static String encryptMsisdn(String msisdn,String encryptKey) throws Exception {
        return base64Encode(aesEncryptToBytes(msisdn,getAESKey(encryptKey),"AES/ECB/PKCS5Padding"));
    }
}
