package cn.innovation.platform.upms.common.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 上游渠道城市区域配置表
 * </p>
 *
 * @author Administrator
 * @since 2018-12-25
 */
@TableName("tb_api_area")
public class TbApiArea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 公司ID
     */
	@TableField("company_id")
	private Integer companyId;
    /**
     * 公司名称
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 上游渠道编码
     */
	@TableField("api_code")
	private String apiCode;
    /**
     * 省份
     */
	private String province;
    /**
     * 城市集合(多个城市以逗号分隔)
     */
	private String citys;
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCitys() {
		return citys;
	}

	public void setCitys(String citys) {
		this.citys = citys;
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
		return "TbApiArea{" +
			", id=" + id +
			", companyId=" + companyId +
			", companyName=" + companyName +
			", apiCode=" + apiCode +
			", province=" + province +
			", citys=" + citys +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
