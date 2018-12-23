package cn.innovation.platform.upms.common.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * @ClassName: TbAppInfo
 * @Description: 下游渠道信息
 * @author mqx
 * @date 2018年12月11日 下午10:43:09
 */
@TableName("tb_app_info")
public class TbAppInfo implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 应用密码
     */
	@TableField("app_secret")
	private String appSecret;
    /**
     * 渠道码
     */
	@TableField("channel_code")
	private String channelCode;
    /**
     * 渠道
     */
	private String channel;
    /**
     * 渠道类型(1:赠险,2:贷款)
     */
	@TableField("channel_type")
	private Integer channelType;
    /**
     * 子渠道
     */
	@TableField("sub_channel")
	private String subChannel;
    /**
     * 对接上游API列表,多个以逗号分隔
     */
	@TableField("api_list")
	private String apiList;
    /**
     * 状态(0:正常,1:停用)
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

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public String getSubChannel() {
		return subChannel;
	}

	public void setSubChannel(String subChannel) {
		this.subChannel = subChannel;
	}

	public String getApiList() {
		return apiList;
	}

	public void setApiList(String apiList) {
		this.apiList = apiList;
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
		return "TbAppInfo{" +
			", id=" + id +
			", appSecret=" + appSecret +
			", channelCode=" + channelCode +
			", channel=" + channel +
			", channelType=" + channelType +
			", subChannel=" + subChannel +
			", apiList=" + apiList +
			", status=" + status +
			", version=" + version +
			", contacts=" + contacts +
			", company=" + company +
			", mobile=" + mobile +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
