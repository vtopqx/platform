package cn.innovation.platform.insurance.common.utils;

import java.io.Serializable;

/**
 * @ClassName: InsuranceApiHelper
 * @Description: 接口请求参数(insurance)
 * @author mqx
 * @date 2018年4月9日 下午1:48:05
 */
public class InsuranceApiHelper implements Serializable {

	private static final long serialVersionUID = -1670501615252362445L;

	// 大坝Url
	private String damdataUrl;

	// 畅思接口URL
	private String vinsunUrl;

	public InsuranceApiHelper(String damdataUrl, String vinsunUrl) {
		super();
		this.damdataUrl = damdataUrl;
		this.vinsunUrl = vinsunUrl;
	}

	public InsuranceApiHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDamdataUrl() {
		return damdataUrl;
	}

	public void setDamdataUrl(String damdataUrl) {
		this.damdataUrl = damdataUrl;
	}

	public String getVinsunUrl() {
		return vinsunUrl;
	}

	public void setVinsunUrl(String vinsunUrl) {
		this.vinsunUrl = vinsunUrl;
	}

}
