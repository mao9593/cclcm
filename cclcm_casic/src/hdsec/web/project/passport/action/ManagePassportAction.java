package hdsec.web.project.passport.action;

import hdsec.web.project.passport.model.EntityPassport;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagePassportAction extends PassportBaseAction {

	private static final long serialVersionUID = 1L;
	private String passport_num = "";
	private Integer passport_type = null;
	private Integer passport_status = null;
	private String duty_dept_id = "";
	private String duty_user_id = "";
	private String duty_dept_name = "";
	private String duty_user_name = "";
	private List<EntityPassport> passportList = null;
	private Date startTime = null;
	private Date endTime = null;
	private List<String> depts = null;

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

	public Integer getPassport_type() {
		return passport_type;
	}

	public void setPassport_type(Integer passport_type) {
		this.passport_type = passport_type;
	}

	public Integer getPassport_status() {
		return passport_status;
	}

	public void setPassport_status(Integer passport_status) {
		this.passport_status = passport_status;
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

	public List<EntityPassport> getPassportList() {
		return passportList;
	}

	public void setPassportList(List<EntityPassport> passportList) {
		this.passportList = passportList;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
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
		map.put("passport_type", passport_type);
		map.put("passport_status", passport_status);
		if (!duty_dept_id.equals("")) {
			getAllDept(duty_dept_id);
			map.put("duty_dept_id", depts);
		}
		map.put("duty_user_iidd", duty_user_id);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		passportList = passportService.getAllPassportList(map);
		return SUCCESS;
	}
}
