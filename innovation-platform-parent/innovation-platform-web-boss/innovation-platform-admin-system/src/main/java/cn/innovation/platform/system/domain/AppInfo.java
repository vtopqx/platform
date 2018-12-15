package cn.innovation.platform.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.innovation.platform.common.base.BaseEntity;
import java.util.Date;

/**
 * 应用注册表 tb_app_info
 * 
 * @author mqx
 * @date 2018-12-13
 */
public class AppInfo extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** 主键ID 应用Key */
	private Integer id;
	/** 渠道 */
	private String channel;
	/** 子渠道 */
	private String subChannel;
	/** 应用密码 */
	private String appSecret;
	/** 状态(0:禁用,1:启用) */
	private Integer status;
	/** 接口版本 */
	private String version;
	/** 联系人 */
	private String contacts;
	/** 公司名称 */
	private String company;
	/** 联系号码 */
	private String mobile;
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

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getChannel() {
		return channel;
	}

	public void setSubChannel(String subChannel) {
		this.subChannel = subChannel;
	}

	public String getSubChannel() {
		return subChannel;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getVersion() {
		return version;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContacts() {
		return contacts;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCompany() {
		return company;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
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
				.append("channel", getChannel()).append("subChannel", getSubChannel())
				.append("appSecret", getAppSecret()).append("status", getStatus()).append("version", getVersion())
				.append("contacts", getContacts()).append("company", getCompany()).append("mobile", getMobile())
				.append("createTime", getCreateTime()).append("updateTime", getUpdateTime()).toString();
	}
}
