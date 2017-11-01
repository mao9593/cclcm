package hdsec.web.project.asset.action;

import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.asset.model.EntityProperty;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 资产出库、报废状态变更
 * 
 * @author gaoximin 2015-7-18
 */
public class UpdatePropertyStatusAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private String property_barcode = "";
	private Integer property_status = null;
	private String result = "";
	private String type = "";
	private EntityProperty property = null;

	public void setType(String type) {
		this.type = type;
	}

	public String getProperty_barcode() {
		return property_barcode;
	}

	public void setProperty_barcode(String property_barcode) {
		this.property_barcode = property_barcode;
	}

	public Integer getProperty_status() {
		return property_status;
	}

	public void setProperty_status(Integer property_status) {
		this.property_status = property_status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String executeFunction() throws Exception {
		if (property_barcode.isEmpty()) {
			setResult("参数错误，没有资产条码");
		} else {
			// 更新资产状态
			assetService
					.updatePropertyStatus(property_barcode, property_status);
			Map<String, String> map = new HashMap<String, String>();
			map.put("property_barcode", property_barcode);
			property = assetService.getPropertyByIDBarcode(map);
			// 生成载体生命周期记录
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(property.getProperty_barcode());
			cycleitem.setEntity_type("PROPERTY");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(getCurUser().getUser_name());
			cycleitem.setDept_name(getCurUser().getDept_name());
			if (property_status == 2) {// 出库
				cycleitem.setOper("PROPERTYOUT");
				assetService.addCycleItem(cycleitem);
				setResult("已出库");
				insertCommonLog("资产已出库[" + property_barcode + "]");
			} else if (property_status == 4) {// 报废
				cycleitem.setOper("WASTE");
				assetService.addCycleItem(cycleitem);
				setResult("已报废");
				insertCommonLog("资产已报废[" + property_barcode + "]");
			} else if (property_status == 0) {// 入库
				cycleitem.setOper("PROPERTYIN");
				assetService.addCycleItem(cycleitem);
				setResult("已入库");
				insertCommonLog("资产已入库[" + property_barcode + "]");
			}
			return type.equalsIgnoreCase("ajax") ? null : "destroy";
		}
		return SUCCESS;
	}

}
