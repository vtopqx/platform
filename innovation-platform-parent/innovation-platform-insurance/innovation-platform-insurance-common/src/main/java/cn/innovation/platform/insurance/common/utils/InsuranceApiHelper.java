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

	// 号码归属地接口地址
	private String mobileUrl;

	// 大坝Url
	private String damdataUrl;
	// 大坝密钥
	private String damdataSecret;

	// 畅思接口URL
	private String vinsunUrl;
	private String vinsunMchId;
	private String vinsunMedia;
	private String vinsunSecret;

	public InsuranceApiHelper(String damdataUrl, String damdataSecret, String vinsunUrl, String vinsunMchId,
			String vinsunMedia, String vinsunSecret, String mobileUrl) {
		super();
		this.damdataUrl = damdataUrl;
		this.damdataSecret = damdataSecret;
		this.vinsunUrl = vinsunUrl;
		this.vinsunMchId = vinsunMchId;
		this.vinsunMedia = vinsunMedia;
		this.vinsunSecret = vinsunSecret;
		this.mobileUrl = mobileUrl;
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

	public String getDamdataSecret() {
		return damdataSecret;
	}

	public void setDamdataSecret(String damdataSecret) {
		this.damdataSecret = damdataSecret;
	}

	public String getVinsunMchId() {
		return vinsunMchId;
	}

	public void setVinsunMchId(String vinsunMchId) {
		this.vinsunMchId = vinsunMchId;
	}

	public String getVinsunMedia() {
		return vinsunMedia;
	}

	public void setVinsunMedia(String vinsunMedia) {
		this.vinsunMedia = vinsunMedia;
	}

	public String getVinsunSecret() {
		return vinsunSecret;
	}

	public void setVinsunSecret(String vinsunSecret) {
		this.vinsunSecret = vinsunSecret;
	}

	public String getMobileUrl() {
		return mobileUrl;
	}

	public void setMobileUrl(String mobileUrl) {
		this.mobileUrl = mobileUrl;
	}

}
