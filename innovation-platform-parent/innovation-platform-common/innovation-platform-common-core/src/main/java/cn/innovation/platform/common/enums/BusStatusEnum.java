package cn.innovation.platform.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: HeiNiuBusStatusEnum
 * @Description: 黑牛业务状态
 * @author mqx
 * @date 2018年9月25日 下午1:45:43
 */
public enum BusStatusEnum {
	creditCard("1", "信用卡"), insurance("2", "保险"), loan("3", "贷款");

	private String id;
	private String desc;

	BusStatusEnum(String id, String desc) {
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
		for (BusStatusEnum status : values()) {
			if (StringUtils.equals(status.getId(), id)) {
				return status.getDesc();
			}
		}
		return id;
	}
}
