package hdsec.web.project.asset.action;

import hdsec.web.project.asset.model.PropertyKind;

/**
 * 添加资产类型
 * 
 * @author gaoximin 2015-7-18
 */
public class AddKindAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private String typename = "";
	private String property_prefix = "";
	private String add = "N";

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public String getProperty_prefix() {
		return property_prefix;
	}

	public void setProperty_prefix(String property_prefix) {
		this.property_prefix = property_prefix;
	}

	@Override
	public String executeFunction() throws Exception {
		if (add.equalsIgnoreCase("Y")) {
			PropertyKind type = new PropertyKind();
			type.setProperty_kind(typename);
			type.setProperty_prefix(property_prefix);
			assetService.addKindType(type);
			insertCommonLog("添加资产类型:" + typename);
			return "insert";
		} else
			return SUCCESS;
	}
}
