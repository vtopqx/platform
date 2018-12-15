package cn.innovation.platform.upms.common.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * @ClassName: TbAppInfo
 * @Description: 应用注册信息表
 * @author mqx
 * @date 2018年12月11日 下午10:43:09
 */
@TableName("tb_app_info")
public class TbAppInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;
	/**
	 * 渠道
	 */
	private String channel;
	/**
	 * 子渠道
	 */
	@TableField("sub_channel")
	private String subChannel;
	/**
	 * 应用密码
	 */
	@TableField("app_secret")
	private String appSecret;
	/**
	 * 状态(0:禁用,1:启用)
	 */
	private Integer status;
	/**
	 * 接口版本
	 */
	private String version;
	/**
	 * 联系人
	 */
	private String contacts;
	/**
	 * 公司名称
	 */
	private String company;
	/**
	 * 联系号码
	 */
	private String mobile;
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

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSubChannel() {
		return subChannel;
	}

	public void setSubChannel(String subChannel) {
		this.subChannel = subChannel;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
		return "TbAppInfo{" + ", id=" + id + ", channel=" + channel + ", subChannel=" + subChannel + ", appSecret="
				+ appSecret + ", status=" + status + ", version=" + version + ", contacts="
				+ contacts + ", company=" + company + ", mobile=" + mobile + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "}";
	}
}
