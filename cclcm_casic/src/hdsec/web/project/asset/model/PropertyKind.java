package hdsec.web.project.asset.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 资产类型
 * 
 * @author gaoximin 2015-7-18
 */
public class PropertyKind extends BaseDomain {
	private Integer id = null;
	private String property_kind = "";
	private String property_prefix = "";
	private Integer serial_no = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProperty_kind() {
		return property_kind;
	}

	public String getProperty_prefix() {
		return property_prefix;
	}

	public void setProperty_prefix(String property_prefix) {
		this.property_prefix = property_prefix;
	}

	public void setProperty_kind(String property_kind) {
		this.property_kind = property_kind;
	}

	public Integer getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(Integer serial_no) {
		this.serial_no = serial_no;
	}

}
