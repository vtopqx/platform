package cn.innovation.platform.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
	 * @param data
	 * @return
	 */
	public static boolean isNotEmpty(Object data) {
		return (data != null) ? true : false;
	}
	
	/**
	 * @Description: 判断是否为空
	 * @param data
	 * @return
	 */
	public static boolean isNotEmpty(String data) {
		return (data != null && data.trim().length()>0) ? true : false;
	}

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
	
	public static String concatParamsUtf(Map<String, Object> params) throws UnsupportedEncodingException {
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
	
	/**
	 * @Description: 连接参数
	 * @param params 参数集合
	 * @return
	 */
	public static String concatParams(Map<String, Object> params) {
		Object[] key_arr = params.keySet().toArray();
		Arrays.sort(key_arr);
		String str = "";
		for (Object key : key_arr) {
			if (key.equals("sign")) {
				continue;
			}
			String val = params.get(key).toString();
			key = key.toString();
			str += "&" + key + "=" + val;
		}
		return str.replaceFirst("&", "");
	}
	
	
	/**
	 * @Description: 根据身份证/生日获取年龄
	 * @param idNo 身份证号
	 * @param birth 生日
	 * @return
	 * @throws Exception
	 */
	public static int getAge(String idNo, String birth) throws Exception {
		String birthday = "";
		if (StringUtils.isNotEmpty(idNo)) {
			if (idNo.length() == 18) {
				birthday = idNo.substring(6, 10) + "-" + idNo.substring(10, 12) + "-" + idNo.substring(12, 14);
			}
		} else {
			birthday = birth;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		Date tmpBirth = new Date();
		tmpBirth = sdf.parse(birthday);
		long intervalMilli = now.getTime() - tmpBirth.getTime();
		int age = (int) (intervalMilli / (24 * 60 * 60 * 1000)) / 365;
		return age;
	}
	 
}
