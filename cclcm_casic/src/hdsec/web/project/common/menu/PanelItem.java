package hdsec.web.project.common.menu;

/**
 * 说明: 左侧滚动菜单项目
 * @author renmingfei
 *
 */
public class PanelItem {
	protected String caption;
	protected String url;
	protected String target;
	protected String icon;
	protected String hideLeft;

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

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHideLeft() {
		return hideLeft;
	}

	public void setHideLeft(String hideLeft) {
		this.hideLeft = hideLeft;
	}
}
