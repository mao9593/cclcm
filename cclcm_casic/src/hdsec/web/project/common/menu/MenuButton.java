package hdsec.web.project.common.menu;

import java.util.List;

/**
 * 说明: 主菜单相应属性
 * @author renmingfei
 *
 */
public class MenuButton {

	protected String caption;
	/** 内容框架的url */
	protected String url;
	/** 按下按钮后的脚本, 可以open window等 */
	protected String onclick;
	protected String panelstate;
	protected String icon;
	protected List<PanelGroup> panels;

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public List<PanelGroup> getPanels() {
		return panels;
	}

	public void setPanels(List<PanelGroup> panels) {
		this.panels = panels;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPanelstate() {
		return panelstate;
	}

	public void setPanelstate(String panelstate) {
		this.panelstate = panelstate;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}