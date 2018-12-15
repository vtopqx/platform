package cn.innovation.platform.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.innovation.platform.common.base.BaseEntity;
import java.util.Date;

/**
 * 大坝保险申请记录表 tb_insurance_damdata_records
 * 
 * @author mqx
 * @date 2018-12-13
 */
public class InsuranceDamdataRecords extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 主键ID */
	private Integer id;
	/** 应用Key */
	private String appKey;
	/** 渠道 */
	private String channel;
	/** 请求时间，格式：yyyyMMddHHmmss */
	private String requestTime;
	/** 签名（加密后的值填此处） */
	private String sign;
	/** 用户名，必须是2到8位的中文字符 */
	private String name;
	/** 用户性别，1=男，0=女 */
	private String sex;
	/** 出生日期，格式：yyyy-MM-dd */
	private String birthDay;
	/** 手机号码 */
	private String mobile;
	/** 用户年龄 */
	private String age;
	/** 用户来源地址，如：http://www.google.com */
	private String referer;
	/** 用户IP */
	private String ip;
	/**
	 * 用户头信息，如：Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101
	 * Firefox/29.0
	 */
	private String userAgent;
	/** 是否持卡，选值：已有信用卡、无信用卡 */
	private String hadCredit;
	/** 正在申请的信用卡(默认值:其他) */
	private String applyCredit;
	/** 状态(0:发送中,1:发送成功,-1:发送失败,-2不符合发送条件) */
	private String status;
	/** 返回值 */
	private String result;
	/** 数据创建时间 */
	private Date createTime;
	/** 数据更新时间 */
	private Date updateTime;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannel() {
		return channel;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign() {
		return sign;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAge() {
		return age;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getReferer() {
		return referer;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return ip;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setHadCredit(String hadCredit) {
		this.hadCredit = hadCredit;
	}

	public String getHadCredit() {
		return hadCredit;
	}

	public void setApplyCredit(String applyCredit) {
		this.applyCredit = applyCredit;
	}

	public String getApplyCredit() {
		return applyCredit;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", getId())
				.append("appKey", getAppKey()).append("channel", getChannel())
				.append("requestTime", getRequestTime()).append("sign", getSign()).append("name", getName())
				.append("sex", getSex()).append("birthDay", getBirthDay()).append("mobile", getMobile())
				.append("age", getAge()).append("referer", getReferer()).append("ip", getIp())
				.append("userAgent", getUserAgent()).append("hadCredit", getHadCredit())
				.append("applyCredit", getApplyCredit()).append("status", getStatus()).append("result", getResult())
				.append("createTime", getCreateTime()).append("updateTime", getUpdateTime()).toString();
	}
}
