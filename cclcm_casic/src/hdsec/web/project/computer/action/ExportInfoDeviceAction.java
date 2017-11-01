package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.BMCommonExportAction;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportInfoDeviceAction extends BMCommonExportAction {

	private static final long serialVersionUID = 1L;
	private String device_barcode = "";// 设备条码编号
	private String duty_user_id = "";// 责任人ID
	private String duty_dept_id = "";// 责任人ID
	private String conf_code = "";// 保密编号
	private String device_series = "";// 设备编号
	private Integer device_type = null;// 设备类型（1: 办公自动化设备2:外部设备3:安全产品4:介质5:其他）
	private String info_id = "";// 详细设备ID
	private Integer device_statues = null;// 设备状态
	private Integer device_seclv = null;// 设备密级
	private String type = "";
	private String userid = "";
	private String dept_ids = "";
	private List<String> depts = null;

	private final String filename = "信息设备台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "信息设备台账记录";
	private final String[] titles = { "序号", "设备条码", "设备类型", "子设备类型", "责任人", "责任人部门", "设备密级", "单位", "原保密编号", "保密编号",
			"资产编号", "设备用途", "品牌", "型号/序列号", "采购时间", "启用时间", "报废时间", "安装地点", "检测证书名称", "证书编号", "内存/容量", "设备状态", "备注" };

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getDevice_series() {
		return device_series;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public Integer getDevice_type() {
		return device_type;
	}

	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}

	public String getInfo_id() {
		return info_id;
	}

	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

	public Integer getDevice_statues() {
		return device_statues;
	}

	public void setDevice_statues(Integer device_statues) {
		this.device_statues = device_statues;
	}

	public Integer getDevice_seclv() {
		return device_seclv;
	}

	public void setDevice_seclv(Integer device_seclv) {
		this.device_seclv = device_seclv;
	}

	public String getFilename() {
		return filename;
	}

	public String getSheetName() {
		return sheetName;
	}

	public String[] getTitles() {
		return titles;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids;
	}

	private void getAllDept(String dept) {
		depts = new ArrayList<String>();
		String temp[] = dept.split(",");
		for (int i = 0; i < temp.length; i++) {
			depts.add(temp[i]);
		}
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("device_barcode", device_barcode);
		map.put("conf_code", conf_code);
		map.put("device_series", device_series);
		map.put("device_type", device_type);
		map.put("info_id", info_id);
		map.put("device_statues", device_statues);
		map.put("device_seclv", device_seclv);
		if (type.equals("SELF") && !userid.equals("") && !type.equals("ALL")) {
			map.put("duty_user_id", userid);
			OutputStream os = createFile(filename);
			exportFileBM(os, map, sheetName, titles, "device");
		} else {
			map.put("duty_user_id", duty_user_id);
			if (!duty_dept_id.equals("")) {
				getAllDept(duty_dept_id);
				map.put("duty_dept_id", depts);
			}
			if (!dept_ids.equals("")) {
				map.put("scope_dept_ids", dept_ids.split(","));
			}
			OutputStream os = createFile(filename);
			exportFileBM(os, map, sheetName, titles, "device");
		}

		return SUCCESS;
	}
}