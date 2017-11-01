package hdsec.web.project.common.menu;

import java.util.List;

/**
 * 说明: 左侧滚动菜单组对象
 * @author renmingfei
 *
 */
public class PanelGroup {
	protected String caption;
	/** 内容框架的url */
	protected String url;
	/** 按下按钮后的脚本, 可以open window等 */
	protected String onclick;
	protected List<PanelItem> items;

	public List<PanelItem> getItems() {
		return items;
	}

	public void setItems(List<PanelItem> items) {
		this.items = items;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}