package cn.innovation.platform.insurance.common.enums;

/**
 * @ClassName: HttpCodeUtils 
 * @Description: 状态码说明
 * @author mqx 
 * @date 2017年11月24日 上午9:04:52
 */
public enum VinsunCodeEnum {
	
	CODE_1001("1001", "提交成功"), 
	CODE_1010("1010", "重复提交了相同的数据"), 
	CODE_1101("1101", "上级接口返回异常"),
	CODE_1005("1005", "号码校验不通过"), 
	CODE_302("302", "数据同步失败"),
	CODE_4("-4", "没有符合的产品可以领取");


	// 成员变量
	private String code;
	private String remark;

	// 构造方法
	private VinsunCodeEnum(String code, String remark) {
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
