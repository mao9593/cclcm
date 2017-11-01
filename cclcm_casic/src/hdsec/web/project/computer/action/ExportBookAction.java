package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.BMCommonExportAction;
import hdsec.web.project.user.model.SecUser;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportBookAction extends BMCommonExportAction {

	private static final long serialVersionUID = 1L;
	private String book_barcode = "";
	private String seclv_code = "";
	private String book_code = "";
	private String computer_code = "";
	private Integer computer_status = null;
	private String duty_dept_id = "";
	private String duty_user_id = "";
	private String conf_code = "";
	private String dept_ids = "";
	private List<String> depts = null;
	private String type = "";

	private final String filename = "笔记本台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "笔记本台账记录";
	private final String[] titles = { "序号", "统一编号", "原保密编号", "保密编号", "单位", "使用部门", "保管人", "密级", "系统时间", "硬盘序列号",
			"MAG地址", "联网情况", "名称型号", "存放地点", "使用情况", "外出情况", "输出情况", "存储涉密信息情况", "备注" };

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids;
	}

	public String getBook_code() {
		return book_code;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getBook_barcode() {
		return book_barcode;
	}

	public void setBook_barcode(String book_barcode) {
		this.book_barcode = book_barcode;
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
		map.put("book_barcode", book_barcode);
		map.put("conf_code", conf_code);
		map.put("seclv_code", seclv_code);
		map.put("book_code", book_code);
		map.put("book_status", computer_status);
		if (!duty_dept_id.equals("")) {
			getAllDept(duty_dept_id);
			map.put("duty_dept_id", depts);
		}
		if (type.equals("SELF")) {
			map.put("duty_user_id", getCurUser().getUser_iidd());
		} else {
			map.put("duty_user_id", duty_user_id);
		}
		if (type.equals("DEPT")) {
			map.put("scope_dept_ids", dept_ids.split(","));
		}

		OutputStream os = createFile(filename);
		exportFileBM(os, map, sheetName, titles, "book");
		return SUCCESS;
	}

}