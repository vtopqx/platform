package cn.innovation.platform.system.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import cn.innovation.platform.common.base.BaseEntity;

/**
 * 下游渠道表 tb_app_info
 * @author mqx
 * @date 2018-12-22
 */
public class AppInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer id;
	/** 应用密码 */
	private String appSecret;
	/** 渠道码 */
	private String channelCode;
	/** 渠道 */
	private String channel;
	/** 渠道类型(1:赠险,2:贷款) */
	private Integer channelType;
	/** 子渠道 */
	private String subChannel;
	/** 对接上游API列表,多个以逗号分隔 */
	private String apiList;
	/** 状态(0:正常,1:停用) */
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

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setAppSecret(String appSecret) 
	{
		this.appSecret = appSecret;
	}

	public String getAppSecret() 
	{
		return appSecret;
	}
	public void setChannelCode(String channelCode) 
	{
		this.channelCode = channelCode;
	}

	public String getChannelCode() 
	{
		return channelCode;
	}
	public void setChannel(String channel) 
	{
		this.channel = channel;
	}

	public String getChannel() 
	{
		return channel;
	}
	public void setChannelType(Integer channelType) 
	{
		this.channelType = channelType;
	}

	public Integer getChannelType() 
	{
		return channelType;
	}
	public void setSubChannel(String subChannel) 
	{
		this.subChannel = subChannel;
	}

	public String getSubChannel() 
	{
		return subChannel;
	}
	public void setApiList(String apiList) 
	{
		this.apiList = apiList;
	}

	public String getApiList() 
	{
		return apiList;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setVersion(String version) 
	{
		this.version = version;
	}

	public String getVersion() 
	{
		return version;
	}
	public void setContacts(String contacts) 
	{
		this.contacts = contacts;
	}

	public String getContacts() 
	{
		return contacts;
	}
	public void setCompany(String company) 
	{
		this.company = company;
	}

	public String getCompany() 
	{
		return company;
	}
	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public String getMobile() 
	{
		return mobile;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appSecret", getAppSecret())
            .append("channelCode", getChannelCode())
            .append("channel", getChannel())
            .append("channelType", getChannelType())
            .append("subChannel", getSubChannel())
            .append("apiList", getApiList())
            .append("status", getStatus())
            .append("version", getVersion())
            .append("contacts", getContacts())
            .append("company", getCompany())
            .append("mobile", getMobile())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
