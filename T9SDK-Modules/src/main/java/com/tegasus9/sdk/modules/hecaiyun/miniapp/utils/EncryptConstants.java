package com.tegasus9.sdk.modules.hecaiyun.miniapp.utils;

/**
 * 加密常量
 * @author yuanpeng
 * @date 2022年4月21日
 */
public class EncryptConstants {
	
	private EncryptConstants() {}
	
	private static final String ALGO_AES = "AES";
	private static final String TRANSFORMATION_ECB = "AES/ECB/PKCS5Padding";
	public static final String TRANSFORMATION_CBC = "AES/CBC/PKCS5Padding";
	public static final String SHA_256 = "SHA-256";

	public static final String ALGO_DES = "DES";
	public static final String TRANSFORMATION_DES_ECB = "DES/ECB/NoPadding";


	public static String getAesAlogTag() {
		return ALGO_AES;
	}
	
	public static String getAesEcbTransformation() {
		return TRANSFORMATION_ECB;
	}
}
