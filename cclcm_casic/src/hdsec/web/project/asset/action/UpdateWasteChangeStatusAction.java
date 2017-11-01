package hdsec.web.project.asset.action;

import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.asset.model.WasteEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 资产出库、报废状态变更
 * 
 * @author gaoximin 2015-7-30
 */
public class UpdateWasteChangeStatusAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private Integer property_status = null;
	private WasteEvent event = null;

	public void setProperty_status(Integer property_status) {
		this.property_status = property_status;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		try {
			// 更新资产状态
			event = assetService.getWasteEventByCode(event_code);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", event.getProperty_id());
			map.put("property_status", property_status);
			if (property_status == 4) {// 报废
				map.put("waste_time", new Date());
				assetService.updateWasteStatus(event_code, 1);
				assetService.updatePropertyStatusByID(map);
			} else if (property_status == 0) {// 变更
				assetService.updateWasteStatus(event_code, 2);
				map.put("duty_user_iidd", event.getUser_iidd_after());
				map.put("duty_user_name", event.getUser_name_after());
				map.put("duty_dept_id", event.getDept_id_after());
				map.put("duty_dept_name", event.getDept_name_after());
				assetService.updatePropertyStatusByID(map);
			}

			// 生成载体生命周期记录
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(event.getProperty_barcode());
			cycleitem.setEntity_type("PROPERTY");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(getCurUser().getUser_name());
			cycleitem.setDept_name(getCurUser().getDept_name());
			if (property_status == 4) {// 报废
				cycleitem.setOper("WASTE");
				assetService.addCycleItem(cycleitem);
				insertCommonLog("资产已报废[" + event.getProperty_name() + "]");
			} else if (property_status == 0) {// 变更
				cycleitem.setOper("CHANGE");
				assetService.addCycleItem(cycleitem);
				insertCommonLog("资产已变更[" + event.getProperty_name() + "]");
			}
			return SUCCESS;
		} catch (Exception e) {
			logger.error("Exception" + e.getMessage());
			throw new Exception("操作出现异常");
		}
	}
}
