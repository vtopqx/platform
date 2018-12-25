package cn.innovation.platform.upms.common.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * @ClassName: TbApiInfo 
 * @Description: 上游渠道信息表 
 * @author mqx 
 * @date 2018年12月23日 下午4:08:10
 */
@TableName("tb_api_info")
public class TbApiInfo implements Serializable {


	private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 上游渠道编码
     */
	@TableField("api_code")
	private String apiCode;
    /**
     * 上游渠道名称
     */
	@TableField("api_name")
	private String apiName;
    /**
     * 显示顺序
     */
	@TableField("api_sort")
	private Integer apiSort;
    /**
     * 渠道类型(1:赠险,2:贷款)
     */
	@TableField("channel_type")
	private Integer channelType;
    /**
     * 状态(0:正常,1:停用)
     */
	private Integer status;
    /**
     * 联系人
     */
	private String contacts;
    /**
     * 公司名称
     */
	private String company;
    /**
     * 公司ID
     */
	@TableField("company_id")
	private Integer companyId;
    /**
     * 联系号码
     */
	private String mobile;
    /**
     * 年龄限制
     */
	@TableField("age_limit")
	private String ageLimit;
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

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public Integer getApiSort() {
		return apiSort;
	}

	public void setApiSort(Integer apiSort) {
		this.apiSort = apiSort;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(String ageLimit) {
		this.ageLimit = ageLimit;
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
		return "TbApiInfo{" +
			", id=" + id +
			", apiCode=" + apiCode +
			", apiName=" + apiName +
			", apiSort=" + apiSort +
			", channelType=" + channelType +
			", status=" + status +
			", contacts=" + contacts +
			", company=" + company +
			", companyId=" + companyId +
			", mobile=" + mobile +
			", ageLimit=" + ageLimit +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
