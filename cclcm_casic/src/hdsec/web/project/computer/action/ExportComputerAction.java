package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.BMCommonExportAction;
import hdsec.web.project.user.model.SecUser;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportComputerAction extends BMCommonExportAction {

	private static final long serialVersionUID = 1L;
	private String computer_barcode = "";
	private String seclv_code = "";
	private String computer_code = "";
	private Integer med_type = null;
	private Integer computer_status = null;
	private String duty_dept_id = "";
	private String duty_user_id = "";
	private String conf_code = "";
	private String dept_ids = "";
	private String type = "N";
	private List<String> depts = null;

	private final String filename = "计算机台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "计算机台账记录";
	private final String[] titles = { "序号", "设备类型", "计算机名称型号", "统一编号", "原保密编号", "保密编号", "密级", "责任人", "责任部门", "责任单位",
			"联接网络", "操作系统", "安装时间", "硬盘序列号", "KEY编号", "IP", "掩码", "MAC", "输出端口", "放置区域", "放置地点", "使用情况", "VLAN", "网关",
			"交换机", "端口号", "录入时间", "安全产品安装情况", "备注" };

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComputer_barcode() {
		return computer_barcode;
	}

	public void setComputer_barcode(String computer_barcode) {
		this.computer_barcode = computer_barcode;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getComputer_code() {
		return computer_code;
	}

	public void setComputer_code(String computer_code) {
		this.computer_code = computer_code;
	}

	public Integer getMed_type() {
		return med_type;
	}

	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
	}

	public Integer getComputer_status() {
		return computer_status;
	}

	public void setComputer_status(Integer computer_status) {
		this.computer_status = computer_status;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	protected SecUser curUser = getSecUserFromSession();

	public SecUser getCurUser() {
		return curUser;
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
		if (type.contains("SELF")) {
			duty_user_id = getCurUser().getUser_iidd();
		} else if (type.contains("DEPT") || type.contains("D")) {
			map.put("scope_dept_ids", dept_ids.split(","));
		}

		map.put("conf_code", conf_code);
		map.put("seclv_code", seclv_code);
		map.put("computer_code", computer_code);
		map.put("computer_status", computer_status);
		if (!duty_dept_id.equals("")) {
			getAllDept(duty_dept_id);
			map.put("duty_dept_id", depts);
		}
		map.put("duty_user_id", duty_user_id);
		OutputStream os = createFile(filename);
		exportFileBM(os, map, sheetName, titles, "computer");
		return SUCCESS;
	}

}