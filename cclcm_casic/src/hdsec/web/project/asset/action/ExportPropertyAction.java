package hdsec.web.project.asset.action;

import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.asset.model.EntityProperty;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出资产总台账
 * 
 * @author yueying
 * 
 */
public class ExportPropertyAction extends PropertyExportAction {
	private static final long serialVersionUID = 1L;

	private List<EntityProperty> SelfPropertyList = null;
	private String property_name = "";
	private String property_barcode = "";
	private String seclv_name = "";
	private String duty_user_name = "";
	private Date in_time = null;
	private String property_status_str;
	private Integer expire_status = null;

	private final String filename = "资产台账记录-" + ".xls";
	private final String sheetName = "资产总台账记录";
	private final String[] titles = { "序号", "采购人", "采购部门", "责任人", "责任人部门",
			"资产密级", "资产种类", "设备名称", "资产条码", "资产状态", "识别码", "资产编号", "凭证号",
			"出厂编号", "出厂日期", "启用日期", "单价", "规格", "型号", "国别厂家", "原值", "净值",
			"安装地点", "入账时间", "报废时间", "备注" };

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("user_iidd", getSecUserFromSession().getUser_iidd());
		map.put("property_name", property_name);
		map.put("property_barcode", property_barcode);
		map.put("seclv_name", seclv_name);
		map.put("duty_user_name", duty_user_name);
		map.put("in_time", in_time);
		map.put("property_status_str", property_status_str);
		map.put("expire_status", expire_status);
		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "selfproperty");

		return null;
	}

	public List<EntityProperty> getSelfPropertyList() {
		return SelfPropertyList;
	}

	public void setSelfPropertyList(List<EntityProperty> selfPropertyList) {
		SelfPropertyList = selfPropertyList;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public String getProperty_barcode() {
		return property_barcode;
	}

	public void setProperty_barcode(String property_barcode) {
		this.property_barcode = property_barcode;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public Date getIn_time() {
		return in_time;
	}

	public void setIn_time(Date in_time) {
		this.in_time = in_time;
	}

	public String getProperty_status_str() {
		return property_status_str;
	}

	public void setProperty_status_str(String property_status_str) {
		this.property_status_str = property_status_str;
	}

	public String getSheetName() {
		return sheetName;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}
}
