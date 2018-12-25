package cn.innovation.platform.upms.common.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 上游渠道公司表
 * </p>
 *
 * @author Administrator
 * @since 2018-12-25
 */
@TableName("tb_api_company")
public class TbApiCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 公司名称
     */
	private String name;
    /**
     * 状态(0:正常,1:停用)
     */
	private Integer status;
    /**
     * 联系人
     */
	private String contacts;
    /**
     * 联系号码
     */
	private String mobile;
    /**
     * 备注
     */
	private String remark;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return "TbApiCompany{" +
			", id=" + id +
			", name=" + name +
			", status=" + status +
			", contacts=" + contacts +
			", mobile=" + mobile +
			", remark=" + remark +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
