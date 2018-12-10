package cn.innovation.platform.common.base;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author 刘利民
 *
 *         统一返回结果类
 */
public class BaseResult implements Serializable {

	/**
	 * 状态码：1、成功 其他为失败
	 */
	public int code;

	/**
	 * 成功为sucess，其他为失败原因
	 */
	public String msg;

	/**
	 * 返回成功时为结果集 返回失败时不显示
	 */
	public Object data;

	public BaseResult() {
	}

	public BaseResult(int code, String message) {
		this.code = code;
		this.msg = message;
	}

	public BaseResult(int code, String message, Object data) {
		this.code = code;
		this.msg = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
