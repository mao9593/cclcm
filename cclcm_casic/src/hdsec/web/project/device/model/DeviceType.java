package hdsec.web.project.device.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 磁介质类型类
 * 
 * @author lixiang
 * 
 */
public class DeviceType extends BaseDomain {
	private Integer id = null;
	private String typename = "";
	private String content = "";

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
