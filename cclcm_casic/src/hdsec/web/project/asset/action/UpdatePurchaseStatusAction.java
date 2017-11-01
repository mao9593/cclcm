package hdsec.web.project.asset.action;

import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.asset.model.EntityProperty;
import hdsec.web.project.asset.model.PurchaseEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 更新入库状态，并生成资产台账
 * 
 * @author gaoximin 2015-6-27
 */
public class UpdatePurchaseStatusAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private String property_barcode = ""; // 资产条码
	private String event_code = "";// 作业ID(外键)
	private String user_iidd = "";// 采购申请人ID
	private String user_name = "";// 采购人
	private String dept_id = "";// 采购部门ID
	private String dept_name = "";// 采购部门
	private String duty_user_iidd = "";// 责任人ID
	private String duty_user_name = "";// 责任人姓名
	private String duty_dept_id = "";// 责任人部门名称
	private String duty_dept_name = "";// 责任人部门名称
	private Integer seclv_code = null;// 资产密级
	private String property_kind = ""; // 采购资产种类
	private String property_name = ""; // 资产名称
	private String property_standard = ""; // 资产规格型号
	private String supplier = ""; // 供应厂商
	private String unit_price = ""; // 单价
	private Date in_time = null; // 入库时间

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {

		// 修改采购作业状态
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("store_status", 1);

		PurchaseEvent event = assetService.getPurchaseEventByCode(event_code);
		user_iidd = event.getUser_iidd();
		user_name = event.getUser_name();
		dept_id = event.getDept_id();
		dept_name = event.getDept_name();
		duty_user_iidd = getCurUser().getUser_iidd();
		duty_user_name = getCurUser().getUser_name();
		duty_dept_id = getCurUser().getDept_id();
		duty_dept_name = getCurUser().getDept_name();
		seclv_code = event.getProperty_seclvcode();
		property_kind = event.getProperty_kind();
		property_name = event.getProperty_name();
		property_standard = event.getProperty_standard();
		supplier = event.getSupplier();
		unit_price = event.getUnit_price();

		// String usage_code = event.getUsage_code();
		// String project_code = event.getProject_code();
		// SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code,
		// usage_code, project_code);
		// StringBuffer sb = new StringBuffer();

		// 生成条码，暂未做打印条码
		int property_num = event.getAmount();
		for (int i = 0; i < property_num; i++) {
			property_barcode = basicService.createEntityBarcode("PROPERTY");
			EntityProperty property = null;
			property = new EntityProperty(property_barcode, event_code,
					user_iidd, user_name, dept_id, dept_name, duty_user_iidd,
					duty_user_name, duty_dept_id, duty_dept_name, seclv_code,
					property_kind, property_name, property_standard, supplier,
					unit_price, new Date(), "", "", "", "", "", null, null, "",
					"", "", "");

			// 生成资产生命周期记录
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(property_barcode);
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(getCurUser().getUser_name());
			cycleitem.setDept_name(getCurUser().getDept_name());
			cycleitem.setOper("PURCHASEIN");
			cycleitem.setEntity_type("PROPERTY");

			assetService.addPropertyledger(map, map, property, cycleitem);
			insertCommonLog("资产入库[" + property_name + property_barcode + "]");
		}
		return SUCCESS;
	}
}
