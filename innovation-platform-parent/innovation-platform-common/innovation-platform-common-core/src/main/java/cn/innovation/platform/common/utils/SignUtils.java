package cn.innovation.platform.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: SignUtils
 * @Description: 签名工具类
 * @author admin
 * @date 2018年12月17日 下午8:52:42
 */
public class SignUtils {

	/**
	 * @Description: 计算签名示例
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 组装参数
		Map<String, Object> params = new HashMap<>();
		params.put("name", "test");
		params.put("sex", "1");
		//签名密钥
		String appSecret = "12345";
		// 计算签名
		String sig = genSig(params, appSecret);
		System.out.println("sig=====>" + sig);
	}

	private static String concatParams(Map<String, Object> params) throws UnsupportedEncodingException {
		Object[] key_arr = params.keySet().toArray();
		Arrays.sort(key_arr);
		String str = "";
		for (Object key : key_arr) {
			if (key.equals("sign")) {
				continue;
			}
			String val = params.get(key).toString();
			key = URLEncoder.encode(key.toString(), "UTF-8");
			val = URLEncoder.encode(val, "UTF-8");
			str += "&" + key + "=" + val;
		}

		return str.replaceFirst("&", "");
	}

	private static String byte2hex(byte[] b) {
		StringBuffer buf = new StringBuffer();
		int i;

		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0) {
				i += 256;
			}
			if (i < 16) {
				buf.append("0");
			}
			buf.append(Integer.toHexString(i));
		}

		return buf.toString();
	}

	/**
	 * @Description: 获取签名字符串
	 * @param params 参数集合Map
	 * @param secret 密钥
	 */
	public static String genSig(Map<String, Object> params, String secret) throws Exception {
		StringBuffer stringBuffer = new StringBuffer(concatParams(params));
		stringBuffer.append(secret);
		MessageDigest md = MessageDigest.getInstance("SHA1");
		String sig = byte2hex(md.digest(byte2hex(stringBuffer.toString().getBytes("UTF-8")).getBytes()));
		return sig;
	}
}
