package cn.innovation.platform.insurance.common.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 畅思保险申请记录表
 * </p>
 *
 * @author Administrator
 * @since 2018-12-16
 */
@TableName("tb_insurance_vinsun_records")
public class VinsunRecords implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 数据编号
	 */
	@TableField("history_id")
	private Integer historyId;
	/**
	 * 应用Key
	 */
	@TableField("app_key")
	private String appKey;
	/**
	 * 渠道
	 */
	private String channel;
	/**
	 * 请求时间，格式：yyyyMMddHHmmss
	 */
	@TableField("request_time")
	private String requestTime;
	/**
	 * MD5签名
	 */
	private String sign;
	/**
	 * 商户ID
	 */
	@TableField("mch_id")
	private String mchId;
	/**
	 * 商户订单号
	 */
	@TableField("sp_billno")
	private String spBillno;
	/**
	 * 用户名称
	 */
	private String name;
	/**
	 * 用户性别，1=男，0=女
	 */
	private String sex;
	/**
	 * 用户生日（如：1987-01-26）
	 */
	private String birth;
	/**
	 * 用户电话
	 */
	private String mobile;
	/**
	 * 用户IP
	 */
	@TableField("client_ip")
	private String clientIp;
	/**
	 * 用户城市（根据用户IP匹配的用户城市）
	 */
	@TableField("client_city")
	private String clientCity;
	/**
	 * 媒体编码
	 */
	private String media;
	/**
	 * 用户代理
	 */
	private String ua;
	/**
	 * 用户身份证
	 */
	@TableField("user_card")
	private String userCard;
	/**
	 * 备注
	 */
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
	@TableField("create_time")
	private Date createTime;
	/**
	 * 数据更新时间
	 */
	@TableField("update_time")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
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

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getUserCard() {
		return userCard;
	}

	public void setUserCard(String userCard) {
		this.userCard = userCard;
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

	@Override
	public String toString() {
		return "TbInsuranceVinsunRecords{" + ", id=" + id + ", historyId=" + historyId + ", appKey=" + appKey
				+ ", channel=" + channel + ", requestTime=" + requestTime + ", sign=" + sign + ", mchId=" + mchId
				+ ", spBillno=" + spBillno + ", name=" + name + ", sex=" + sex + ", birth=" + birth + ", mobile="
				+ mobile + ", clientIp=" + clientIp + ", clientCity=" + clientCity + ", media=" + media + ", ua=" + ua
				+ ", userCard=" + userCard + ", remark=" + remark + ", status=" + status + ", result=" + result
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
	}
}
