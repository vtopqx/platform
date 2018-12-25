package cn.innovation.platform.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.innovation.platform.common.base.BaseEntity;
import java.util.Date;

/**
 * 上游渠道城市区域配置表 tb_api_area
 * 
 * @author mqx
 * @date 2018-12-24
 */
public class ApiArea extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer id;
	/** 公司ID */
	private Integer companyId;
	/** 公司名称 */
	private String companyName;
	/** 上游渠道编码 */
	private String apiCode;
	/** 省份 */
	private String province;
	/** 城市集合(多个城市以逗号分隔) */
	private String citys;
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
	public void setCompanyId(Integer companyId) 
	{
		this.companyId = companyId;
	}

	public Integer getCompanyId() 
	{
		return companyId;
	}
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	public String getCompanyName() 
	{
		return companyName;
	}
	public void setApiCode(String apiCode) 
	{
		this.apiCode = apiCode;
	}

	public String getApiCode() 
	{
		return apiCode;
	}
	public void setProvince(String province) 
	{
		this.province = province;
	}

	public String getProvince() 
	{
		return province;
	}
	public void setCitys(String citys) 
	{
		this.citys = citys;
	}

	public String getCitys() 
	{
		return citys;
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
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .append("apiCode", getApiCode())
            .append("province", getProvince())
            .append("citys", getCitys())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
