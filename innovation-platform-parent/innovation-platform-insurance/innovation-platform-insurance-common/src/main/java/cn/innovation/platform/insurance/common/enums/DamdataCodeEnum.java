package cn.innovation.platform.insurance.common.enums;

/**
 * @ClassName: HttpCodeUtils 
 * @Description: 状态码说明
 * @author mqx 
 * @date 2017年11月24日 上午9:04:52
 */
public enum DamdataCodeEnum {
	
	SUCCESS("SUCCESS", "成功"), 
	FAIL("FAIL", "失败"), 
	REPEAT("REPEAT", "重复提交了相同的数据"),
	WAIT("WAIT", "处理中"), 
	FREEZES("FREEZES", "已冻结"), 
	PARAM_FAIL("PARAM_FAIL", "参数效验未通过"), 
	SIGN_FAIL("SIGN_FAIL", "签名效验未通过"), 
	CHANNEL_CODE_NOT_FOUND("CHANNEL_CODE_NOT_FOUND", "渠道识别码不存在"), 
	BLACK_FAIL("BLACK_FAIL", "黑名单");


	// 成员变量
	private String code;
	private String remark;

	// 构造方法
	private DamdataCodeEnum(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}
	
	public String value() {
		return this.code;
	}

	public String remark() {
		return this.remark;
	}
	
    @Override  
    public String toString() {  
        return this.code+"";  
    } 
}
