package cn.innovation.platform.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.innovation.platform.common.base.BaseEntity;
import java.util.Date;

/**
 * 上游渠道表 tb_api_info
 * 
 * @author mqx
 * @date 2018-12-22
 */
public class ApiInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer id;
	/** 上游渠道编码 */
	private String apiCode;
	/** 上游渠道名称 */
	private String apiName;
	/** 显示顺序 */
	private Integer apiSort;
	/** 渠道类型(1:赠险,2:贷款) */
	private Integer channelType;
	/** 状态(0:正常,1:停用) */
	private Integer status;
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
	
	private boolean flag;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setApiCode(String apiCode) 
	{
		this.apiCode = apiCode;
	}

	public String getApiCode() 
	{
		return apiCode;
	}
	public void setApiName(String apiName) 
	{
		this.apiName = apiName;
	}

	public String getApiName() 
	{
		return apiName;
	}
	public void setApiSort(Integer apiSort) 
	{
		this.apiSort = apiSort;
	}

	public Integer getApiSort() 
	{
		return apiSort;
	}
	public void setChannelType(Integer channelType) 
	{
		this.channelType = channelType;
	}

	public Integer getChannelType() 
	{
		return channelType;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
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

    public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("apiCode", getApiCode())
            .append("apiName", getApiName())
            .append("apiSort", getApiSort())
            .append("channelType", getChannelType())
            .append("status", getStatus())
            .append("contacts", getContacts())
            .append("company", getCompany())
            .append("mobile", getMobile())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
