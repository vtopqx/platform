package cn.innovation.platform.common.utils;

import java.util.Base64;
import java.util.Scanner;

/**
 * @ClassName: Base64Utils 
 * @Description: Base64加密工具类
 * @author mqx 
 * @date 2018年12月10日 上午11:02:47
 */
public class Base64Utils {

	/**
	 * @Description: 加密
	 * @param str 明文字符串
	 * @return
	 * @throws Exception
	 */
	public static String encode(String str) throws Exception {
		return Base64.getEncoder().encodeToString(str.getBytes("utf-8"));
	}

	/**
	 * @Description: 示例
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String data = "测试";
			String sign = Base64Utils.encode(data);
			System.out.println("加密后字符串：" + sign);
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
