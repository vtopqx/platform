package cn.innovation.platform.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: StringUtils
 * @Description: String工具类
 * @author mqx
 * @date 2018年12月16日 下午9:46:35
 */
public class StringUtils {

	/**
	 * @Description: 判断是否为空
	 * @param map
	 * @return
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return (map != null && map.size() > 0) ? true : false;
	}

	/**
	 * @Description: 判断是否为空
	 * @param list
	 * @return
	 */
	public static boolean isNotEmpty(List<?> list) {
		return (list != null && list.size() > 0) ? true : false;
	}

	/**
	 * @Description: 判断是否为空
	 * @param list
	 * @return
	 */
	public static boolean isNotEmpty(Object[] array) {
		return (array != null && array.length > 0) ? true : false;
	}

	/**
	 * @Description: 接口请求参数拼接
	 * @param params
	 *            参数结婚
	 * @return
	 */
	public static String getRequestUrl(String apiUrl, Map<String, String> params) {
		String requestUrl = apiUrl;
		StringBuffer sbf = new StringBuffer();
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				String val = params.get(key);
				sbf.append("&").append(key.toLowerCase()).append("=").append(val);
			}
			requestUrl = requestUrl + "?" + sbf.substring(1);
		}
		return requestUrl;
	}
	
	private static String concatParams(Map<String, Object> params) throws UnsupportedEncodingException {
        Object[] key_arr = params.keySet().toArray();
        Arrays.sort(key_arr);
        String str = "";

        for (Object key : key_arr) {
            if (key.equals("signature")) {
                continue;
            }
            String val = params.get(key).toString();
            key = URLEncoder.encode(key.toString(), "UTF-8");
            val = URLEncoder.encode(val, "UTF-8");
            str += "&" + key + "=" + val;
        }

        return str.replaceFirst("&", "");
    }
	 
	 
}
