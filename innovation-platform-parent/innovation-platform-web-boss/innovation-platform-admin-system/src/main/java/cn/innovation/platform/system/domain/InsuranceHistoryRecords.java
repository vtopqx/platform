package cn.innovation.platform.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.innovation.platform.common.base.BaseEntity;
import java.util.Date;

/**
 * 保险申请记录汇总表 tb_insurance_history_records
 * 
 * @author mqx
 * @date 2018-12-22
 */
public class InsuranceHistoryRecords extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键ID */
	private Integer id;
	/** 应用Key */
	private String appKey;
	/** 渠道 */
	private String channel;
	/** 请求时间，格式：yyyyMMddHHmmss */
	private String requestTime;
	/** MD5签名 */
	private String sign;
	/** 上游渠道编码 */
	private String apiCode;
	/** 商户ID */
	private String mchId;
	/** 商户订单号 */
	private String spBillno;
	/** 用户名称 */
	private String name;
	/** 用户性别，1=男，0=女 */
	private String sex;
	/** 年龄 */
	private String age;
	/** 用户生日（如：1987-01-26） */
	private String birth;
	/** 用户电话 */
	private String mobile;
	/** 用户IP */
	private String clientIp;
	/** 用户城市（根据用户IP匹配的用户城市） */
	private String clientCity;
	/** 媒体编码 */
	private String media;
	/** 用户代理 */
	private String userAgent;
	/** 用户身份证 */
	private String userCard;
	/** 是否持卡，选值：已有信用卡、无信用卡 */
	private String hadCredit;
	/** 正在申请的信用卡(默认值:其他) */
	private String applyCredit;
	/** 用户来源地址，如：http://www.google.com */
	private String referer;
	/** 备注 */
	private String remark;
	/** 状态(0:发送中,1:发送成功,-1:发送失败,-2:不符合发送条件,-3:号码不合法,-4:发送成功但不结算,-5:发送成功延迟结算) */
	private String status;
	/** 返回值 */
	private String result;
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
	public void setAppKey(String appKey) 
	{
		this.appKey = appKey;
	}

	public String getAppKey() 
	{
		return appKey;
	}
	public void setChannel(String channel) 
	{
		this.channel = channel;
	}

	public String getChannel() 
	{
		return channel;
	}
	public void setRequestTime(String requestTime) 
	{
		this.requestTime = requestTime;
	}

	public String getRequestTime() 
	{
		return requestTime;
	}
	public void setSign(String sign) 
	{
		this.sign = sign;
	}

	public String getSign() 
	{
		return sign;
	}
	public void setApiCode(String apiCode) 
	{
		this.apiCode = apiCode;
	}

	public String getApiCode() 
	{
		return apiCode;
	}
	public void setMchId(String mchId) 
	{
		this.mchId = mchId;
	}

	public String getMchId() 
	{
		return mchId;
	}
	public void setSpBillno(String spBillno) 
	{
		this.spBillno = spBillno;
	}

	public String getSpBillno() 
	{
		return spBillno;
	}
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	public void setSex(String sex) 
	{
		this.sex = sex;
	}

	public String getSex() 
	{
		return sex;
	}
	public void setAge(String age) 
	{
		this.age = age;
	}

	public String getAge() 
	{
		return age;
	}
	public void setBirth(String birth) 
	{
		this.birth = birth;
	}

	public String getBirth() 
	{
		return birth;
	}
	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public String getMobile() 
	{
		return mobile;
	}
	public void setClientIp(String clientIp) 
	{
		this.clientIp = clientIp;
	}

	public String getClientIp() 
	{
		return clientIp;
	}
	public void setClientCity(String clientCity) 
	{
		this.clientCity = clientCity;
	}

	public String getClientCity() 
	{
		return clientCity;
	}
	public void setMedia(String media) 
	{
		this.media = media;
	}

	public String getMedia() 
	{
		return media;
	}
	public void setUserAgent(String userAgent) 
	{
		this.userAgent = userAgent;
	}

	public String getUserAgent() 
	{
		return userAgent;
	}
	public void setUserCard(String userCard) 
	{
		this.userCard = userCard;
	}

	public String getUserCard() 
	{
		return userCard;
	}
	public void setHadCredit(String hadCredit) 
	{
		this.hadCredit = hadCredit;
	}

	public String getHadCredit() 
	{
		return hadCredit;
	}
	public void setApplyCredit(String applyCredit) 
	{
		this.applyCredit = applyCredit;
	}

	public String getApplyCredit() 
	{
		return applyCredit;
	}
	public void setReferer(String referer) 
	{
		this.referer = referer;
	}

	public String getReferer() 
	{
		return referer;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}
	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String getStatus() 
	{
		return status;
	}
	public void setResult(String result) 
	{
		this.result = result;
	}

	public String getResult() 
	{
		return result;
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
            .append("appKey", getAppKey())
            .append("channel", getChannel())
            .append("requestTime", getRequestTime())
            .append("sign", getSign())
            .append("apiCode", getApiCode())
            .append("mchId", getMchId())
            .append("spBillno", getSpBillno())
            .append("name", getName())
            .append("sex", getSex())
            .append("age", getAge())
            .append("birth", getBirth())
            .append("mobile", getMobile())
            .append("clientIp", getClientIp())
            .append("clientCity", getClientCity())
            .append("media", getMedia())
            .append("userAgent", getUserAgent())
            .append("userCard", getUserCard())
            .append("hadCredit", getHadCredit())
            .append("applyCredit", getApplyCredit())
            .append("referer", getReferer())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("result", getResult())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }	
}
