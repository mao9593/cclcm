package hdsec.web.project.asset.action;

import hdsec.web.project.asset.model.PropertyKind;

/**
 * 删除资产类型
 * 
 * @author gaoximin 2015-7-18
 */
public class DelKindAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer id = null;
	private String typename = "";

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		PropertyKind type = assetService.getKindTypeByID(id);
		typename = type.getProperty_kind();
		assetService.delKindTypeByID(id);
		insertCommonLog("删除资产类型:" + typename);

		return SUCCESS;
	}
}
