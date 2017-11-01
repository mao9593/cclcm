package hdsec.web.project.asset.action;

import hdsec.web.project.asset.model.PropertyKind;

/**
 * 修改资产类型
 * 
 * @author gaoximin 2015-7-18
 */
public class UpdateKindAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private PropertyKind type = null;
	private Integer id = null;
	private String typename = "";
	private String property_prefix = "";
	private String update = "N";
	private Integer serial_no = 0;

	public PropertyKind getType() {
		return type;
	}

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

	public String getProperty_prefix() {
		return property_prefix;
	}

	public void setProperty_prefix(String property_prefix) {
		this.property_prefix = property_prefix;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public Integer getSerial_no() {
		return serial_no;
	}

	public void setSerial_no(Integer serial_no) {
		this.serial_no = serial_no;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {
			type = new PropertyKind();
			type.setId(id);
			type.setProperty_prefix(property_prefix);
			type.setProperty_kind(typename);
			type.setSerial_no(serial_no);
			assetService.updateKindType(type);
			insertCommonLog("修改资产类型:" + typename);
			return "update";
		} else {
			type = assetService.getKindTypeByID(id);
		}
		return SUCCESS;
	}
}
