package cn.innovation.platform.framework.util;

/**
 * @ClassName: ComboBoxUtils 
 * @Description: easyui下拉框工具类
 * @author mqx 
 * @date 2018年9月24日 下午12:59:55
 */
public class ComboBoxUtils {

	private String id;
	private String value;
	private String text;
	private boolean selected;

	public ComboBoxUtils(String id, String value, String text) {
		super();
		this.id = id;
		this.value = value;
		this.text = text;
	}

	public ComboBoxUtils(String id, String value, String text, boolean selected) {
		super();
		this.id = id;
		this.value = value;
		this.text = text;
		this.selected = selected;
	}

	public ComboBoxUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
