package cn.innovation.platform.upms.common.model;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @ClassName: ApplicationRegisterInfo 
 * @Description: 应用注册信息
 * @author mqx 
 * @date 2017年12月7日 下午1:45:16
 */
@TableName("application_register_info")
public class ApplicationRegisterInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 创新平台分配给第三方平台的consumerKey
     */
	@TableField("consumer_key")
	private String consumerKey;
    /**
     * 应用密码
     */
	@TableField("consumer_secret")
	private String consumerSecret;
    /**
     * 应用帐号类型(0:时效应用,1:长期应用)
     */
	@TableField("consumer_type")
	private String consumerType;
    /**
     * 企业ID 
     */
	@TableField("enterprise_id")
	private String enterpriseId;
    /**
     * 企业名称
     */
	@TableField("enterprise_name")
	private String enterpriseName;
    /**
     * 应用名称
     */
	@TableField("app_name")
	private String appName;
    /**
     * 注册人手机号码
     */
	@TableField("reg_mobile")
	private String regMobile;
    /**
     * 注册人姓名
     */
	@TableField("reg_user")
	private String regUser;
    /**
     * 注册人邮箱
     */
	@TableField("reg_email")
	private String regEmail;

    /**
     * 调用接口语言类别，例如：php、java、dotnet
     */
	@TableField("sdk_from")
	private String sdkFrom;
    /**
     * 版本号
     */
	private String version;
    /**
     * 数据更新时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 数据创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 状态(1:正常,0:禁用,-1:已删除,-2:已失效)
     */
	private String statuses;
    /**
     * 有效开始时间
     */
	@TableField("valid_start_time")
	private String validStartTime;
    /**
     * 有效截至时间
     */
	@TableField("valid_end_time")
	private String validEndTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
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

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatuses() {
		return statuses;
	}

	public void setStatuses(String statuses) {
		this.statuses = statuses;
	}

	public String getValidStartTime() {
		return validStartTime;
	}

	public void setValidStartTime(String validStartTime) {
		this.validStartTime = validStartTime;
	}

	public String getValidEndTime() {
		return validEndTime;
	}

	public void setValidEndTime(String validEndTime) {
		this.validEndTime = validEndTime;
	}

	@Override
	public String toString() {
		return "ApplicationRegisterInfo{" +
			", id=" + id +
			", consumerKey=" + consumerKey +
			", consumerSecret=" + consumerSecret +
			", consumerType=" + consumerType +
			", enterpriseId=" + enterpriseId +
			", enterpriseName=" + enterpriseName +
			", appName=" + appName +
			", regMobile=" + regMobile +
			", regUser=" + regUser +
			", regEmail=" + regEmail +
			", sdkFrom=" + sdkFrom +
			", version=" + version +
			", updateTime=" + updateTime +
			", createTime=" + createTime +
			", statuses=" + statuses +
			", validStartTime=" + validStartTime +
			", validEndTime=" + validEndTime +
			"}";
	}
}