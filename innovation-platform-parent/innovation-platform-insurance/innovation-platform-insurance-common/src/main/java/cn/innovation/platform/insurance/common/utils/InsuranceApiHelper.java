package cn.innovation.platform.insurance.common.utils;

import java.io.Serializable;
import java.util.Map;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

/**
 * @ClassName: InsuranceApiHelper
 * @Description: 接口请求参数(insurance)
 * @author mqx
 * @date 2018年4月9日 下午1:48:05
 */
public class InsuranceApiHelper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1670501615252362445L;

	private static final Log logger = LogFactory.get();
	
	// 统一认证(token认证)接口域名地址
	private String tokenValidateUrl;

	public InsuranceApiHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsuranceApiHelper(String tokenValidateUrl) {
		super();
		this.tokenValidateUrl = tokenValidateUrl;
	}

	public String getTokenValidateUrl() {
		return tokenValidateUrl;
	}

	public void setTokenValidateUrl(String tokenValidateUrl) {
		this.tokenValidateUrl = tokenValidateUrl;
	}

	/**
	 * @Description: 接口请求参数拼接
	 * @param params
	 *            参数结婚
	 * @return
	 */
	public String getRequestUrl(String apiName,String interfaceUrl, Map<String, String> params) {
		String requestUrl = null;
		StringBuffer sbf = new StringBuffer();
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				String val = params.get(key);
				sbf.append("&").append(key.toLowerCase()).append("=").append(val);
			}
			requestUrl = interfaceUrl + "?" + sbf.substring(1);
		} else {
			requestUrl = interfaceUrl;
		}
		logger.info("======>请求[umps]({})接口链接:{}", apiName, requestUrl);
		return requestUrl;
	}

}
