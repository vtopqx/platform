package cn.innovation.platform.upms.common.dto;

import java.io.Serializable;

/**
 * @ClassName: AppRegisterInfo
 * @Description: 应用注册信息
 * @author mqx
 * @date 2018年11月19日 下午2:42:59
 */
public class AppRegisterInfoDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 应用ID
	 */
	private String appId;
	/**
	 * 应用密码
	 */
	private String appSecret;

	/**
	 * 应用名称
	 */
	private String appName;

	/**
	 * 请求时间戳
	 */
	private String requestId;

	/**
	 * 应用帐号类型(0:时效应用,1:长期应用)
	 */
	private String consumerType;
	/**
	 * 企业ID
	 */
	private String enterpriseId;
	/**
	 * 企业名称
	 */
	private String enterpriseName;

	/**
	 * 注册人手机号码
	 */
	private String regMobile;
	/**
	 * 注册人姓名
	 */
	private String regUser;
	/**
	 * 注册人邮箱
	 */
	private String regEmail;

	/**
	 * 调用接口语言类别，例如：php、java、dotnet
	 */
	private String sdkFrom;
	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 状态(1:正常,0:禁用,-1:已删除,-2:已失效)
	 */
	private String statuses;

	public AppRegisterInfoDto(String appId, String appSecret, String appName, String requestId, String consumerType,
			String enterpriseId, String enterpriseName, String regMobile, String regUser, String regEmail,
			String sdkFrom, String version, String statuses) {
		super();
		this.appId = appId;
		this.appSecret = appSecret;
		this.appName = appName;
		this.requestId = requestId;
		this.consumerType = consumerType;
		this.enterpriseId = enterpriseId;
		this.enterpriseName = enterpriseName;
		this.regMobile = regMobile;
		this.regUser = regUser;
		this.regEmail = regEmail;
		this.sdkFrom = sdkFrom;
		this.version = version;
		this.statuses = statuses;
	}

	public AppRegisterInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getConsumerType() {
		return consumerType;
	}

	public void setConsumerType(String consumerType) {
		this.consumerType = consumerType;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getRegMobile() {
		return regMobile;
	}

	public void setRegMobile(String regMobile) {
		this.regMobile = regMobile;
	}

	public String getRegUser() {
		return regUser;
	}

	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	public String getRegEmail() {
		return regEmail;
	}

	public void setRegEmail(String regEmail) {
		this.regEmail = regEmail;
	}

	public String getSdkFrom() {
		return sdkFrom;
	}

	public void setSdkFrom(String sdkFrom) {
		this.sdkFrom = sdkFrom;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getStatuses() {
		return statuses;
	}

	public void setStatuses(String statuses) {
		this.statuses = statuses;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

}