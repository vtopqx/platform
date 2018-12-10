package cn.innovation.platform.common.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * @ClassName: AESEncode
 * @Description: AES对称加密工具类
 * @author mqx
 * @date 2018年11月19日 下午4:25:26
 */
public class AESUtils {

	/**
	 * AES加密
	 * 
	 * @param encodeRules
	 *            加密规则（初始化秘钥生成器）
	 * @param content
	 *            数据内容
	 * @return
	 */
	public static String AESEncode(String encodeRules, String content) throws Exception {
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(encodeRules.getBytes());
		keygen.init(128, random);
		SecretKey original_key = keygen.generateKey();
		byte[] raw = original_key.getEncoded();
		SecretKey key = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] byte_encode = content.getBytes("utf-8");
		byte[] byte_AES = cipher.doFinal(byte_encode);
		String AES_encode = new String(Base64.encodeBase64String(byte_AES));
		return AES_encode;
	}

	/**
	 * AES解密
	 * 
	 * @param encodeRules
	 *            解密规则（同加密规则）
	 * @param content
	 *            数据内容
	 * @return
	 */
	public static String AESDncode(String encodeRules, String content) throws Exception {
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.setSeed(encodeRules.getBytes());
		keygen.init(128, random);
		SecretKey original_key = keygen.generateKey();
		byte[] raw = original_key.getEncoded();
		SecretKey key = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] byte_content = Base64.decodeBase64(content);
		byte[] byte_decode = cipher.doFinal(byte_content);
		String AES_decode = new String(byte_decode, "utf-8");
		return AES_decode;
	}

	// 示例
	public static void main(String[] args) {
		try {
			//数据内容
			String content = "test123";
			//加密密钥
			String secret = "ba327bca4fa15b0b0f4207d1bc13def4";
			//加密
			String code = AESUtils.AESEncode(secret, content);
			System.out.println("加密后:" + code);
			String data = AESUtils.AESDncode(secret, code);
			System.out.println("解密后:" + data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
