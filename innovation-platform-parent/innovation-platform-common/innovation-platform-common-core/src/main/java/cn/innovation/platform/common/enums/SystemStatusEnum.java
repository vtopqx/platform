package cn.innovation.platform.common.enums;

/**
 * @ClassName: HttpCodeUtils 
 * @Description: 状态码说明
 * @author mqx 
 * @date 2017年11月24日 上午9:04:52
 */
public enum SystemStatusEnum {
	
	CODE_200(200, "操作成功"), 
	CODE_202(202, "提交了重复的数据"), 
	CODE_400(400, "参数校验错误"), 
	CODE_401(401, "身份验证不通过"), 
	CODE_403(403, "无接口权限,服务器拒绝执行"),
	CODE_404(404, "错误的请求"),
	CODE_413(413, "请求消息体过大"),
	CODE_500(500, "服务器错误"),
	CODE_600(600, "签名错误"),
	CODE_408(408, "请求超时,请重试"),
	CODE_417(417, "操作失败");
	 

	// 成员变量
	private int code;
	private String remark;

	// 构造方法
	private SystemStatusEnum(int code, String remark) {
		this.code = code;
		this.remark = remark;
	}
	
	public int value() {
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
