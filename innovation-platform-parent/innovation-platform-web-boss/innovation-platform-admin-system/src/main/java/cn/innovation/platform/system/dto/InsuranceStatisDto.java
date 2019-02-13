package cn.innovation.platform.system.dto;

import java.io.Serializable;

import cn.innovation.platform.common.annotation.Excel;

/**
 * @ClassName: InsuranceStatisDto
 * @Description: 数据统计对象
 * @author mqx
 * @date 2018年12月28日 上午10:38:56
 */
public class InsuranceStatisDto implements Serializable {

	private static final long serialVersionUID = 1L;

	// 日期
	@Excel(name = "日期")
	private String date;
	// 进件总数
	@Excel(name = "进件总数")
	private String allTotal;
	// 毛名单数
	@Excel(name = "毛名单")
	private String mTotal;
	// 34个省市
	@Excel(name = "北京")
	private String bjTotal;
	
	@Excel(name = "天津")
	private String tjTotal;
	
	@Excel(name = "上海")
	private String shTotal;
	
	@Excel(name = "重庆")
	private String cqTotal;
	
	@Excel(name = "河北")
	private String hbTotal;
	
	@Excel(name = "安徽")
	private String ahTotal;
	
	@Excel(name = "四川")
	private String scTotal;
	
	@Excel(name = "江苏")
	private String jsTotal;
	
	@Excel(name = "浙江")
	private String zjTotal;
	
	@Excel(name = "福建")
	private String fjTotal;
	
	@Excel(name = "山东")
	private String sdTotal;
	
	@Excel(name = "河南")
	private String henTotal;
	
	@Excel(name = "湖北")
	private String hubTotal;
	
	@Excel(name = "湖南")
	private String hunTotal;
	
	@Excel(name = "广东")
	private String gdTotal;
	
	@Excel(name = "广西")
	private String gxTotal;
	
	@Excel(name = "海南")
	private String hainTotal;
	
	@Excel(name = "江西")
	private String jxTotal;
	
	@Excel(name = "山西")
	private String sanxTotal;
	
	@Excel(name = "贵州")
	private String gzTotal;
	
	@Excel(name = "云南")
	private String ynTotal;
	
	@Excel(name = "西藏")
	private String xzTotal;
	
	@Excel(name = "陕西")
	private String sxTotal;
	
	@Excel(name = "辽宁")
	private String lnTotal;
	
	@Excel(name = "吉林")
	private String jlTotal;

	@Excel(name = "内蒙古")
	private String nmgTotal;
	
	@Excel(name = "甘肃")
	private String gsTotal;
	
	@Excel(name = "青海")
	private String qhTotal;
	
	@Excel(name = "宁夏")
	private String nxTotal;
	
	@Excel(name = "新疆")
	private String xjTotal;
	
	@Excel(name = "台湾")
	private String twTotal;
	
	@Excel(name = "香港")
	private String xgTotal;
	
	@Excel(name = "澳门")
	private String amTotal;
	
	@Excel(name = "黑龙江")
	private String hljTotal;

	public InsuranceStatisDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsuranceStatisDto(String date, String allTotal, String mTotal, String bjTotal, String tjTotal,
			String shTotal, String cqTotal, String hbTotal, String ahTotal, String scTotal, String jsTotal,
			String zjTotal, String fjTotal, String sdTotal, String henTotal, String hubTotal, String hunTotal,
			String gdTotal, String gxTotal, String hainTotal, String jxTotal, String sanxTotal, String gzTotal,
			String ynTotal, String xzTotal, String sxTotal, String lnTotal, String jlTotal, String nmgTotal,
			String gsTotal, String qhTotal, String nxTotal, String xjTotal, String twTotal, String xgTotal,
			String amTotal, String hljTotal) {
		super();
		this.date = date;
		this.allTotal = allTotal;
		this.mTotal = mTotal;
		this.bjTotal = bjTotal;
		this.tjTotal = tjTotal;
		this.shTotal = shTotal;
		this.cqTotal = cqTotal;
		this.hbTotal = hbTotal;
		this.ahTotal = ahTotal;
		this.scTotal = scTotal;
		this.jsTotal = jsTotal;
		this.zjTotal = zjTotal;
		this.fjTotal = fjTotal;
		this.sdTotal = sdTotal;
		this.henTotal = henTotal;
		this.hubTotal = hubTotal;
		this.hunTotal = hunTotal;
		this.gdTotal = gdTotal;
		this.gxTotal = gxTotal;
		this.hainTotal = hainTotal;
		this.jxTotal = jxTotal;
		this.sanxTotal = sanxTotal;
		this.gzTotal = gzTotal;
		this.ynTotal = ynTotal;
		this.xzTotal = xzTotal;
		this.sxTotal = sxTotal;
		this.lnTotal = lnTotal;
		this.jlTotal = jlTotal;
		this.nmgTotal = nmgTotal;
		this.gsTotal = gsTotal;
		this.qhTotal = qhTotal;
		this.nxTotal = nxTotal;
		this.xjTotal = xjTotal;
		this.twTotal = twTotal;
		this.xgTotal = xgTotal;
		this.amTotal = amTotal;
		this.hljTotal = hljTotal;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAllTotal() {
		return allTotal;
	}

	public void setAllTotal(String allTotal) {
		this.allTotal = allTotal;
	}

	public String getmTotal() {
		return mTotal;
	}

	public void setmTotal(String mTotal) {
		this.mTotal = mTotal;
	}

	public String getBjTotal() {
		return bjTotal;
	}

	public void setBjTotal(String bjTotal) {
		this.bjTotal = bjTotal;
	}

	public String getTjTotal() {
		return tjTotal;
	}

	public void setTjTotal(String tjTotal) {
		this.tjTotal = tjTotal;
	}

	public String getShTotal() {
		return shTotal;
	}

	public void setShTotal(String shTotal) {
		this.shTotal = shTotal;
	}

	public String getCqTotal() {
		return cqTotal;
	}

	public void setCqTotal(String cqTotal) {
		this.cqTotal = cqTotal;
	}

	public String getHbTotal() {
		return hbTotal;
	}

	public void setHbTotal(String hbTotal) {
		this.hbTotal = hbTotal;
	}

	public String getAhTotal() {
		return ahTotal;
	}

	public void setAhTotal(String ahTotal) {
		this.ahTotal = ahTotal;
	}

	public String getScTotal() {
		return scTotal;
	}

	public void setScTotal(String scTotal) {
		this.scTotal = scTotal;
	}

	public String getJsTotal() {
		return jsTotal;
	}

	public void setJsTotal(String jsTotal) {
		this.jsTotal = jsTotal;
	}

	public String getZjTotal() {
		return zjTotal;
	}

	public void setZjTotal(String zjTotal) {
		this.zjTotal = zjTotal;
	}

	public String getFjTotal() {
		return fjTotal;
	}

	public void setFjTotal(String fjTotal) {
		this.fjTotal = fjTotal;
	}

	public String getSdTotal() {
		return sdTotal;
	}

	public void setSdTotal(String sdTotal) {
		this.sdTotal = sdTotal;
	}

	public String getHenTotal() {
		return henTotal;
	}

	public void setHenTotal(String henTotal) {
		this.henTotal = henTotal;
	}

	public String getHubTotal() {
		return hubTotal;
	}

	public void setHubTotal(String hubTotal) {
		this.hubTotal = hubTotal;
	}

	public String getHunTotal() {
		return hunTotal;
	}

	public void setHunTotal(String hunTotal) {
		this.hunTotal = hunTotal;
	}

	public String getGdTotal() {
		return gdTotal;
	}

	public void setGdTotal(String gdTotal) {
		this.gdTotal = gdTotal;
	}

	public String getGxTotal() {
		return gxTotal;
	}

	public void setGxTotal(String gxTotal) {
		this.gxTotal = gxTotal;
	}

	public String getHainTotal() {
		return hainTotal;
	}

	public void setHainTotal(String hainTotal) {
		this.hainTotal = hainTotal;
	}

	public String getJxTotal() {
		return jxTotal;
	}

	public void setJxTotal(String jxTotal) {
		this.jxTotal = jxTotal;
	}

	public String getSanxTotal() {
		return sanxTotal;
	}

	public void setSanxTotal(String sanxTotal) {
		this.sanxTotal = sanxTotal;
	}

	public String getGzTotal() {
		return gzTotal;
	}

	public void setGzTotal(String gzTotal) {
		this.gzTotal = gzTotal;
	}

	public String getYnTotal() {
		return ynTotal;
	}

	public void setYnTotal(String ynTotal) {
		this.ynTotal = ynTotal;
	}

	public String getXzTotal() {
		return xzTotal;
	}

	public void setXzTotal(String xzTotal) {
		this.xzTotal = xzTotal;
	}

	public String getSxTotal() {
		return sxTotal;
	}

	public void setSxTotal(String sxTotal) {
		this.sxTotal = sxTotal;
	}

	public String getLnTotal() {
		return lnTotal;
	}

	public void setLnTotal(String lnTotal) {
		this.lnTotal = lnTotal;
	}

	public String getJlTotal() {
		return jlTotal;
	}

	public void setJlTotal(String jlTotal) {
		this.jlTotal = jlTotal;
	}

	public String getNmgTotal() {
		return nmgTotal;
	}

	public void setNmgTotal(String nmgTotal) {
		this.nmgTotal = nmgTotal;
	}

	public String getGsTotal() {
		return gsTotal;
	}

	public void setGsTotal(String gsTotal) {
		this.gsTotal = gsTotal;
	}

	public String getQhTotal() {
		return qhTotal;
	}

	public void setQhTotal(String qhTotal) {
		this.qhTotal = qhTotal;
	}

	public String getNxTotal() {
		return nxTotal;
	}

	public void setNxTotal(String nxTotal) {
		this.nxTotal = nxTotal;
	}

	public String getXjTotal() {
		return xjTotal;
	}

	public void setXjTotal(String xjTotal) {
		this.xjTotal = xjTotal;
	}

	public String getTwTotal() {
		return twTotal;
	}

	public void setTwTotal(String twTotal) {
		this.twTotal = twTotal;
	}

	public String getXgTotal() {
		return xgTotal;
	}

	public void setXgTotal(String xgTotal) {
		this.xgTotal = xgTotal;
	}

	public String getAmTotal() {
		return amTotal;
	}

	public void setAmTotal(String amTotal) {
		this.amTotal = amTotal;
	}

	public String getHljTotal() {
		return hljTotal;
	}

	public void setHljTotal(String hljTotal) {
		this.hljTotal = hljTotal;
	}

}
