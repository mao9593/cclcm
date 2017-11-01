package hdsec.web.project.device.action;

import hdsec.web.project.ledger.action.CommonExportAction;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 磁介质管理批量导出
 * 
 * @author congxue 2015-4-18
 */
public class ExportManageDeviceAction extends CommonExportAction {
	private static final long serialVersionUID = 1L;
	private String device_barcode = ""; // 条码号
	private String device_series = "";// 设备编码
	private String seclv_code = "";// 密级
	private Integer med_type;// 类型
	private String device_status = "";// 状态
	private Date startTime = null;// 时间
	private Date endTime = null;
	private final String filename = "磁介质管理台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "磁介质管理台账记录";
	private final String[] titles = { "序号", "条码号", "介质名称", "借用人", "借用人部门", "登记时间", "介质类型", "密级", "部门", "说明", "状态",
			"设备编码", "设备配置", "所属部门", "责任人", "录入员", "磁介质编号", "型号" };

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDevice_series() {
		return device_series;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public Integer getMed_type() {
		return med_type;
	}

	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
	}

	public String getDevice_status() {
		return device_status;
	}

	public void setDevice_status(String device_status) {
		this.device_status = device_status;
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

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("device_barcode", device_barcode);
		map.put("seclv_code", seclv_code);
		map.put("device_series", device_series);
		map.put("med_type", med_type);
		map.put("device_status", device_status);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "device");
		return null;
	}
}
