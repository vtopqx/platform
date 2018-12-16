package cn.innovation.platform.common.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName: MD5Utils 
 * @Description: MD5加密生成32位字符串
 * @author mqx 
 * @date 2018年9月17日 下午3:59:53
 */
public class MD5Utils {

	/***
	 * MD5加密 生成32位md5码
	 * @param 待加密字符串
	 * @return 返回32位md5码
	 */
	public static String md5(String inStr) throws Exception {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
	
	public static String gen_sign(String orig_id, String orig_name, String orig_phone, String channel) throws NoSuchAlgorithmException {
		String target_string = orig_id + orig_name + orig_phone + channel + "baoxian-$@";
		MessageDigest digest = MessageDigest.getInstance("md5");
		digest.update(target_string.getBytes());
		byte[] bs = digest.digest();  
		if(bs == null )  
	        {  
	            return "";  
	        }  
	        StringBuffer sb = new StringBuffer(bs.length);  
	        String sTemp;  
	        for (int i = 0; i < bs.length; i++) {  
	            sTemp = Integer.toHexString(0xFF & bs[i]);  
	            if (sTemp.length() < 2)  
	                sb.append(0);  
	            sb.append(sTemp);  
	        } 
	    System.out.println(sb.toString()+"：md5加密");
	    return sb.toString();  
	}
	
	public static void main(String[] args) {
		// 签名
		try {
			String orig_id = "";
			String orig_name = "何远翔";
			String orig_phone = "13798156379";
			String channel = "139mail";
			String sign = gen_sign(orig_id, orig_name, orig_phone, channel);
			System.out.println(sign);
			
			//加密
			String desStr = DESUtils.encryptDES(orig_name, "6fd95223");
			System.out.println("加密后:"+desStr);
			String codeStr = URLEncoder.encode(desStr, "UTF-8");
			System.out.println("转码后:"+codeStr);
			
			String deCodeStr = URLDecoder.decode("Apache-HttpClient%2F4.1.1+%28java+1.5%29&sex=oopJWEn05Bw%3D&birth=Cd5IqFZFxVDaKi3eh59kaA%3D%3D", "UTF-8");
			System.out.println("解码后:"+deCodeStr);
			String deStr = DESUtils.decryptDES(deCodeStr, "6fd95223");
			System.out.println("解密后:"+deStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
