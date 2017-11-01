package hdsec.web.project.asset.action;

import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.asset.model.EntityProperty;
import hdsec.web.project.asset.model.PropertyKind;
import hdsec.web.project.asset.model.PurchaseEvent;
import hdsec.web.project.asset.model.StorageEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 更新入库状态，并生成资产台账
 * 
 * @author gaoximin 2015-7-29
 */
public class UpdateStorageStatusAction extends AssetBaseAction {
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
	private String supplier = ""; // 国别厂家
	private String unit_price = ""; // 单价

	private String identity_code = "";// 识别码
	private String property_no = "";// 资产编号
	private String voucher_no = ""; // 凭证号
	private String property_type = "";// 资产型号
	private String factory_no = "";// 出厂编号
	private Date factory_date = null; // 出厂日期
	private Date use_date = null;// 启用日期
	private String setup_place = "";// 安装地点
	private String original_value = ""; // 原值
	private String average_equity = ""; // 净值率
	private String summ = ""; // 备注

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {

		// 修改采购、入库作业状态
		Map<String, Object> mapP = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("store_status", 1);

		StorageEvent event = assetService.getStorageEventByCode(event_code);
		// 更新递增流水号
		map.put("kind_id", event.getKind_id());
		if (event != null) {
			PurchaseEvent eventP = assetService.getPurchaseEventByCode(event
					.getEvent_code_purchase());
			mapP.put("event_code", event.getEvent_code_purchase());
			mapP.put("store_status", 1);
			if (eventP != null) {
				user_iidd = eventP.getUser_iidd();
				user_name = eventP.getUser_name();
				dept_id = eventP.getDept_id();
				dept_name = eventP.getDept_name();
				duty_user_iidd = eventP.getUser_iidd();
				duty_user_name = eventP.getUser_name();
				duty_dept_id = eventP.getDept_id();
				duty_dept_name = eventP.getDept_name();
				unit_price = eventP.getUnit_price();
			}
			seclv_code = event.getProperty_seclvcode();
			property_kind = event.getProperty_kind();
			property_name = event.getProperty_name();
			property_standard = event.getProperty_standard();
			supplier = event.getSupplier();

			identity_code = event.getIdentity_code();
			property_no = event.getProperty_no();
			voucher_no = event.getVoucher_no();
			property_type = event.getProperty_type();
			factory_no = event.getFactory_no();
			factory_date = event.getFactory_date();
			use_date = event.getUse_date();
			setup_place = event.getSetup_place();
			original_value = event.getOriginal_value();
			average_equity = event.getAverage_equity();
			summ = event.getSumm();

		}

		// String usage_code = event.getUsage_code();
		// String project_code = event.getProject_code();
		// SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code,
		// usage_code, project_code);
		// StringBuffer sb = new StringBuffer();

		// 生成条码，暂未做打印条码
		int property_num = event.getAmount();
		String serial_number = "";
		for (int i = 0; i < property_num; i++) {
			// 生成编号，与用户沟通后，前缀加4位流水号
			// if (event != null && event.getKind_id() != null) {
			//
			// PropertyKind kind =
			// assetService.getSerialNoByKindId(event.getKind_id());
			// if (kind != null) {
			// if (kind.getSerial_no() < 10) {
			// serial_number = "000" + kind.getSerial_no().toString();
			// } else if (kind.getSerial_no() < 100) {
			// serial_number = "00" + kind.getSerial_no().toString();
			// } else if (kind.getSerial_no() < 1000) {
			// serial_number = "0" + kind.getSerial_no().toString();
			// } else if (kind.getSerial_no() < 10000) {
			// serial_number = "" + kind.getSerial_no().toString();
			// }
			// }
			// property_no = kind.getProperty_prefix() + serial_number;
			// }

			property_barcode = basicService.createEntityBarcode("PROPERTY");
			EntityProperty property = null;
			property = new EntityProperty(property_barcode, event_code,
					user_iidd, user_name, dept_id, dept_name, duty_user_iidd,
					duty_user_name, duty_dept_id, duty_dept_name, seclv_code,
					property_kind, property_name, property_standard, supplier,
					unit_price, new Date(), identity_code, property_no,
					voucher_no, property_type, factory_no, factory_date,
					use_date, setup_place, original_value, average_equity, summ);

			// 生成资产生命周期记录
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(property_barcode);
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(getCurUser().getUser_name());
			cycleitem.setDept_name(getCurUser().getDept_name());
			cycleitem.setOper("PROPERTYIN");
			cycleitem.setEntity_type("PROPERTY");

			assetService.addPropertyledger(map, mapP, property, cycleitem);
			insertCommonLog("资产入库[" + property_name + property_barcode + "]");
		}
		return SUCCESS;
	}
}
