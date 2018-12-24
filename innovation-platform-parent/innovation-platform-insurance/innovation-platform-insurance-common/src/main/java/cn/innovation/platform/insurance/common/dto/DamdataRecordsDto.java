package cn.innovation.platform.insurance.common.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import cn.innovation.platform.common.utils.StringUtils;

/**
 * @ClassName: DamdataRecordsDto
 * @Description: 大坝保险申请记录传输对象
 * @author mqx
 * @date 2018年12月16日 下午6:06:26
 */
public class DamdataRecordsDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 应用Key
	 */
	@NotNull(message = "appKey不能为空")
	@Length(max = 64, message = "appKey长度超过限制")
	private String appKey;
	/**
	 * 渠道
	 */
	@NotNull(message = "channel不能为空")
	@Length(max = 32, message = "channel长度超过限制")
	private String channel;
	/**
	 * 请求时间，格式：yyyyMMddHHmmss
	 */
	@NotNull(message = "requestTime不能为空")
	@Length(max = 32, message = "requestTime长度超过限制")
	private String requestTime;
	/**
	 * 签名（加密后的值填此处）
	 */
	@NotNull(message = "sign不能为空")
	@Length(max = 128, message = "sign长度超过限制")
	private String sign;
	/**
	 * 用户名，必须是2到8位的中文字符
	 */
	@NotNull(message = "name不能为空")
	@Length(max = 32, message = "name长度超过限制")
	private String name;
	/**
	 * 用户性别，1=男，0=女
	 */
	@NotNull(message = "sex不能为空")
	@Length(max = 1, message = "sex长度超过限制")
	private String sex;
	/**
	 * 出生日期，格式：yyyy-MM-dd
	 */
	@NotNull(message = "birthDay不能为空")
	@Length(max = 16, message = "birthDay长度超过限制")
	private String birthDay;
	/**
	 * 手机号码
	 */
	@NotNull(message = "mobile不能为空")
	@Length(max = 16, message = "mobile长度超过限制")
	private String mobile;
	/**
	 * 用户年龄
	 */
	@NotNull(message = "age不能为空")
	@Length(max = 3, message = "age长度超过限制")
	private String age;
	/**
	 * 用户来源地址，如：http://www.google.com
	 */
	@NotNull(message = "referer不能为空")
	@Length(max = 2048, message = "referer长度超过限制")
	private String referer;
	/**
	 * 用户IP
	 */
	private String ip;
	/**
	 * 用户头信息，如：Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101
	 * Firefox/29.0
	 */
	private String userAgent;
	/**
	 * 是否持卡，选值：已有信用卡、无信用卡
	 */
	@NotNull(message = "hadCredit不能为空")
	@Length(max = 16, message = "hadCredit长度超过限制")
	private String hadCredit;
	/**
	 * 正在申请的信用卡(默认值:其他)
	 */
	@Length(max = 32, message = "applyCredit长度超过限制")
	private String applyCredit;
	/**
	 * 状态(0:发送中,1:发送成功,-1:发送失败,-2不符合发送条件)
	 */
	private String status;
	/**
	 * 返回值
	 */
	private String result;
	/**
	 * 数据创建时间
	 */
	private Date createTime;
	/**
	 * 数据更新时间
	 */
	private Date updateTime;

	/**
	 * 数据编号
	 */
	private Integer historyId;
	
	/**
     * 号码归属地(省份)
     */
	private String clientProvince;
	/**
	 * 用户城市（根据用户IP匹配的用户城市）
	 */
	private String clientCity;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getHadCredit() {
		return hadCredit;
	}

	public void setHadCredit(String hadCredit) {
		this.hadCredit = hadCredit;
	}

	public String getApplyCredit() {
		if (!StringUtils.isNotEmpty(applyCredit)) {
			return "其他";
		}
		return applyCredit;
	}

	public void setApplyCredit(String applyCredit) {
		this.applyCredit = applyCredit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}
	
	public String getClientProvince() {
		return clientProvince;
	}

	public void setClientProvince(String clientProvince) {
		this.clientProvince = clientProvince;
	}

	public String getClientCity() {
		return clientCity;
	}

	public void setClientCity(String clientCity) {
		this.clientCity = clientCity;
	}

	@Override
	public String toString() {
		return "DamdataRecordsDto{" +
			", id=" + id +
			", historyId=" + historyId +
			", appKey=" + appKey +
			", channel=" + channel +
			", requestTime=" + requestTime +
			", sign=" + sign +
			", name=" + name +
			", sex=" + sex +
			", birthDay=" + birthDay +
			", mobile=" + mobile +
			", age=" + age +
			", referer=" + referer +
			", ip=" + ip +
			", clientProvince=" + clientProvince +
			", clientCity=" + clientCity +
			", userAgent=" + userAgent +
			", hadCredit=" + hadCredit +
			", applyCredit=" + applyCredit +
			", status=" + status +
			", result=" + result +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}

}
