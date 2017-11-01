package hdsec.web.project.asset.action;

import org.springframework.util.StringUtils;

/**
 * 取消资产变动处理申请
 * 
 * @author gaoximin 2015-7-18
 */
public class CancelHandlePropertyAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private String property_barcode = "";
	private String type = "";
	private String status = "";

	public void setType(String type) {
		this.type = type;
	}

	public String getProperty_barcode() {
		return property_barcode;
	}

	public void setProperty_barcode(String property_barcode) {
		this.property_barcode = property_barcode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(property_barcode)) {
			assetService.cancelHandlePropertyByCode(property_barcode);
			if (status.equals("2")) {
				// 撤销入库，置为出库状态2
				assetService.updatePropertyStatus(property_barcode, 2);
			}
		}
		insertCommonLog("取消资产处理申请[" + property_barcode + "]");
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
