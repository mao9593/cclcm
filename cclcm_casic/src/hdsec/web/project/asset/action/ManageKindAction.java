package hdsec.web.project.asset.action;

import hdsec.web.project.asset.model.PropertyKind;

import java.util.List;

/**
 * 资产类型管理
 * 
 * @author gaoximin 2015-7-18
 */
public class ManageKindAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private List<PropertyKind> typeList = null;

	public List<PropertyKind> getTypeList() {
		return typeList;
	}

	@Override
	public String executeFunction() throws Exception {
		typeList = assetService.getKindList();
		return SUCCESS;
	}
}
