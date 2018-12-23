package cn.innovation.platform.insurance.common.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @ClassName: HistoryRecordsDto
 * @Description: 保险申请记录传输对象
 * @author mqx
 * @date 2018年12月22日 下午11:16:23
 */
public class HistoryRecordsDto implements Serializable {

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
	@Length(max = 128, message = "channel长度超过限制")
	private String channel;
	/**
	 * 请求时间，格式：yyyyMMddHHmmss
	 */
	@NotNull(message = "requestTime不能为空")
	@Length(max = 32, message = "requestTime长度超过限制")
	private String requestTime;
	/**
	 * MD5签名
	 */
	@NotNull(message = "sign不能为空")
	@Length(max = 128, message = "sign长度超过限制")
	private String sign;
	/**
	 * 上游渠道编码
	 */
	@Length(max = 32, message = "apiCode长度超过限制")
	private String apiCode;
	/**
	 * 商户ID
	 */
	private String mchId;
	/**
	 * 商户订单号
	 */
	private String spBillno;
	/**
	 * 用户名称
	 */
	@NotNull(message = "name不能为空")
	@Length(max = 32, message = "name长度超过限制")
	private String name;

	/**
	 * 年龄
	 */
	@Length(max = 3, message = "age长度超过限制")
	private String age;

	/**
	 * 用户性别，1=男，0=女
	 */
	@NotNull(message = "sex不能为空")
	@Length(max = 1, message = "sex长度超过限制")
	private String sex;
	/**
	 * 用户生日（如：1987-01-26）
	 */
	@Length(max = 16, message = "apiCode长度超过限制")
	private String birth;
	/**
	 * 用户电话
	 */
	@NotNull(message = "mobile不能为空")
	@Length(max = 16, message = "mobile长度超过限制")
	private String mobile;
	/**
	 * 用户IP
	 */
	private String clientIp;
	/**
	 * 用户城市（根据用户IP匹配的用户城市）
	 */
	private String clientCity;
	/**
	 * 媒体编码
	 */
	private String media;
	/**
	 * 用户代理
	 */
	private String userAgent;
	/**
	 * 用户身份证
	 */
	@Length(max = 32, message = "apiCode长度超过限制")
	private String userCard;
	/**
	 * 正在申请的信用卡(默认值:其他)
	 */
	@Length(max = 32, message = "applyCredit长度超过限制")
	private String applyCredit;

	@Length(max = 64, message = "hadCredit长度超过限制")
	private String hadCredit;

	/**
	 * 用户来源地址，如：http://www.google.com
	 */
	@Length(max = 2048, message = "referer长度超过限制")
	private String referer;
	/**
	 * 备注
	 */
	@Length(max = 512, message = "referer长度超过限制")
	private String remark;
	/**
	 * 状态(0:发送中,1:发送成功,-1:发送失败,-2:不符合发送条件,-3:号码不合法,-4:发送成功但不结算,-5:发送成功延迟结算)
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

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getSpBillno() {
		return spBillno;
	}

	public void setSpBillno(String spBillno) {
		this.spBillno = spBillno;
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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getClientCity() {
		return clientCity;
	}

	public void setClientCity(String clientCity) {
		this.clientCity = clientCity;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserCard() {
		return userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
	}

	public String getApplyCredit() {
		return applyCredit;
	}

	public void setApplyCredit(String applyCredit) {
		this.applyCredit = applyCredit;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getHadCredit() {
		return hadCredit;
	}

	public void setHadCredit(String hadCredit) {
		this.hadCredit = hadCredit;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "HistoryRecordsDto{" +
			", id=" + id +
			", appKey=" + appKey +
			", channel=" + channel +
			", requestTime=" + requestTime +
			", sign=" + sign +
			", apiCode=" + apiCode +
			", mchId=" + mchId +
			", spBillno=" + spBillno +
			", name=" + name +
			", sex=" + sex +
			", age=" + age +
			", birth=" + birth +
			", mobile=" + mobile +
			", clientIp=" + clientIp +
			", clientCity=" + clientCity +
			", media=" + media +
			", userAgent=" + userAgent +
			", userCard=" + userCard +
			", hadCredit=" + hadCredit +
			", applyCredit=" + applyCredit +
			", referer=" + referer +
			", remark=" + remark +
			", status=" + status +
			", result=" + result +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}

}
