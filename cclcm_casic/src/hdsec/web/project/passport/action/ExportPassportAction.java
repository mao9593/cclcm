package hdsec.web.project.passport.action;

import hdsec.web.project.common.bm.BMCommonExportAction;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportPassportAction extends BMCommonExportAction {

	private static final long serialVersionUID = 1L;
	private String passport_num = "";// 护照编号
	private String passport_type_name;// 护照类型
	private String passport_status_name;// 护照状态
	private String duty_user_iidd = ""; // 责任人ID
	private String duty_user_name = "";// 责任人名称
	private String duty_dept_id = ""; // 责任部门ID
	private String duty_dept_name = ""; // 责任部门名称
	private Date issuing_date = null;// 签发日期
	private String issuing_authority = "";// 签发机关
	private String summ = "";// 备注
	private Date startTime = null;
	private Date endTime = null;
	private List<String> depts = null;

	private final String filename = "人员护照台账" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "人员护照台账";
	private final String[] titles = { "序号", "护照编号", "护照类型", "护照状态", "责任人", "责任部门", "签发日期", "签发机关", "结束日期", "备注" };

	public String getStartTime_str() {
		return startTime == null ? "" : sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime_str() {
		return endTime == null ? "" : sdf.format(endTime);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getPassport_num() {
		return passport_num;
	}

	public void setPassport_num(String passport_num) {
		this.passport_num = passport_num;
	}

	public String getPassport_type_name() {
		return passport_type_name;
	}

	public void setPassport_type_name(String passport_type_name) {
		this.passport_type_name = passport_type_name;
	}

	public String getPassport_status_name() {
		return passport_status_name;
	}

	public void setPassport_status_name(String passport_status_name) {
		this.passport_status_name = passport_status_name;
	}

	public String getDuty_user_iidd() {
		return duty_user_iidd;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public Date getIssuing_date() {
		return issuing_date;
	}

	public void setIssuing_date(Date issuing_date) {
		this.issuing_date = issuing_date;
	}

	public String getIssuing_authority() {
		return issuing_authority;
	}

	public void setIssuing_authority(String issuing_authority) {
		this.issuing_authority = issuing_authority;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
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

	// 前台部门选择框为多选，将多选id存到list类型中在数据库进行查询
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
		map.put("passport_num", passport_num);
		map.put("passport_type_name", passport_type_name);
		map.put("passport_status_name", passport_status_name);
		map.put("duty_user_iidd", duty_user_iidd);
		if (!duty_dept_id.equals("")) {
			getAllDept(duty_dept_id);
			map.put("duty_dept_id", depts);
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);

		OutputStream os = createFile(filename);
		exportFileBM(os, map, sheetName, titles, "passport");
		return SUCCESS;
	}
}