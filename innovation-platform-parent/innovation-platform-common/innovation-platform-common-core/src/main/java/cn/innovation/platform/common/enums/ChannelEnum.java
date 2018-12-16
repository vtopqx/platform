package cn.innovation.platform.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: HeiNiuBusStatusEnum
 * @Description: 黑牛业务状态
 * @author mqx
 * @date 2018年9月25日 下午1:45:43
 */
public enum ChannelEnum {
	all("0", "所有"), directly("1", "直营"), distribution("2", "分销");

	private String id;
	private String desc;

	ChannelEnum(String id, String desc) {
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
		for (ChannelEnum status : values()) {
			if (StringUtils.equals(status.getId(), id)) {
				return status.getDesc();
			}
		}
		return id;
	}
}
