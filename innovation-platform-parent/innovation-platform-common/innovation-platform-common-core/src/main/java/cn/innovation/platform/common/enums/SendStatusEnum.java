package cn.innovation.platform.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: HeiNiuSendStatusEnum
 * @Description: 黑牛接口调用状态
 * @author mqx
 * @date 2018年9月14日 上午11:26:10
 */
public enum SendStatusEnum {
	processing("0", "发送中"), success("1", "发送成功"), failure("-1", "发送失败"), disable("-2", "不满足发送条件"), mobileError("-3",
			"号码不合法"), noBalance("-4", "发送成功但不结算"), delayBalance("-5", "发送成功延迟结算");

	private String id;
	private String desc;

	SendStatusEnum(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static String getDescById(String id) {
		for (SendStatusEnum status : values()) {
			if (StringUtils.equals(status.getId(), id)) {
				return status.getDesc();
			}
		}
		return id;
	}
}
