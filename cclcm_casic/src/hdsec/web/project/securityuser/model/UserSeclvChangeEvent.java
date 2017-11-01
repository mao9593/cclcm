package hdsec.web.project.securityuser.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 用户密级变更、定密作业类
 * 
 * @author gaoxm
 * 
 */

public class UserSeclvChangeEvent extends BaseEvent {

	private String change_user_iidd = ""; // 变更人ID
	private String change_user_name = ""; // 变更人
	private String change_dept_id = ""; // 变更部门ID
	private String change_dept_name = ""; // 变更部门
	private String seclv_code_before = ""; // 变更前密级
	private String seclv_code_after = ""; // 变更后密级、定密
	private String oper_user_iidd = ""; // 变更操作人ID
	private String oper_dept_id = ""; // 变更操作人部门ID
	private Date change_time = null; // 变更时间
	private Integer change_status = 0; // 变更状态
	private String his_job_code = ""; // 包含该作业的历史任务列表
	private String change_type = ""; // 业务流程类型,ADD代表新增涉密人员，CHANGE代表涉密等级表更

	private String seclv_before_name = ""; // 变更前密级
	private String seclv_after_name = ""; // 变更后密级、定密
	private String oper_user_name = ""; // 变更操作人
	private String oper_dept_name = ""; // 变更操作人部门
	private String dept_id_after = ""; // 变更后部门id
	private String dept_after_name = ""; // 变更后部门名称、定部门
	private String post_id_before = ""; // 变更前岗位id
	private String post_before_name = ""; // 变更前岗位名称、定岗
	private String post_id_after = ""; // 变更后岗位id
	private String post_after_name = ""; // 变更后岗位、定岗
	private String post_name_after = ""; // 变更后岗位(20150728按需求更改为输入框，无关联gj)
	private String contact_num = "";// 联系电话

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public String getPost_name_after() {
		return post_name_after;
	}

	public void setPost_name_after(String post_name_after) {
		this.post_name_after = post_name_after;
	}

	public String getChange_user_iidd() {
		return change_user_iidd;
	}

	public void setChange_user_iidd(String change_user_iidd) {
		this.change_user_iidd = change_user_iidd;
	}

	public String getChange_user_name() {
		return change_user_name;
	}

	public void setChange_user_name(String change_user_name) {
		this.change_user_name = change_user_name;
	}

	public String getChange_dept_id() {
		return change_dept_id;
	}

	public void setChange_dept_id(String change_dept_id) {
		this.change_dept_id = change_dept_id;
	}

	public String getChange_dept_name() {
		return change_dept_name;
	}

	public void setChange_dept_name(String change_dept_name) {
		this.change_dept_name = change_dept_name;
	}

	public String getSeclv_code_before() {
		return seclv_code_before;
	}

	public void setSeclv_code_before(String seclv_code_before) {
		this.seclv_code_before = seclv_code_before;
	}

	public String getSeclv_code_after() {
		return seclv_code_after;
	}

	public void setSeclv_code_after(String seclv_code_after) {
		this.seclv_code_after = seclv_code_after;
	}

	public String getOper_user_iidd() {
		return oper_user_iidd;
	}

	public void setOper_user_iidd(String oper_user_iidd) {
		this.oper_user_iidd = oper_user_iidd;
	}

	public String getOper_dept_id() {
		return oper_dept_id;
	}

	public void setOper_dept_id(String oper_dept_id) {
		this.oper_dept_id = oper_dept_id;
	}

	public String getChange_time() {
		return change_time == null ? "" : getSdf().format(change_time);
	}

	public void setChange_time(Date change_time) {
		this.change_time = change_time;
	}

	public Integer getChange_status() {
		return change_status;
	}

	public String getChange_status_name() {
		return change_status == 0 ? "未变更" : "已变更";
	}

	public void setChange_status(Integer change_status) {
		this.change_status = change_status;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getChange_type() {
		return change_type;
	}

	public String getChange_type_name() {
		if (change_type == null) {
			return "";
		}
		switch (change_type) {
		case "ADD":
			return "新增涉密人员";
		case "CHANGE":
			return "涉密人员变更";
		default:
			return "";
		}
	}

	public void setChange_type(String change_type) {
		this.change_type = change_type;
	}

	public String getSeclv_before_name() {
		return seclv_before_name;
	}

	public void setSeclv_before_name(String seclv_before_name) {
		this.seclv_before_name = seclv_before_name;
	}

	public String getSeclv_after_name() {
		return seclv_after_name;
	}

	public void setSeclv_after_name(String seclv_after_name) {
		this.seclv_after_name = seclv_after_name;
	}

	public String getOper_user_name() {
		return oper_user_name;
	}

	public void setOper_user_name(String oper_user_name) {
		this.oper_user_name = oper_user_name;
	}

	public String getOper_dept_name() {
		return oper_dept_name;
	}

	public void setOper_dept_name(String oper_dept_name) {
		this.oper_dept_name = oper_dept_name;
	}

	public UserSeclvChangeEvent() {
		super(JobTypeEnum.USERSECLV_CHANGE);
	}

	public String getPost_id_before() {
		return post_id_before;
	}

	public void setPost_id_before(String post_id_before) {
		this.post_id_before = post_id_before;
	}

	public String getDept_id_after() {
		return dept_id_after;
	}

	public void setDept_id_after(String dept_id_after) {
		this.dept_id_after = dept_id_after;
	}

	public String getPost_id_after() {
		return post_id_after;
	}

	public void setPost_id_after(String post_id_after) {
		this.post_id_after = post_id_after;
	}

	public String getPost_before_name() {
		return post_before_name;
	}

	public void setPost_before_name(String post_before_name) {
		this.post_before_name = post_before_name;
	}

	public String getDept_after_name() {
		return dept_after_name;
	}

	public void setDept_after_name(String dept_after_name) {
		this.dept_after_name = dept_after_name;
	}

	public String getPost_after_name() {
		return post_after_name;
	}

	public void setPost_after_name(String post_after_name) {
		this.post_after_name = post_after_name;
	}

	public UserSeclvChangeEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code,
			String usage_code, String project_code, String summ, String change_user_iidd, String change_user_name,
			String change_dept_id, String change_dept_name, String seclv_code_before, String seclv_code_after,
			String oper_user_iidd, String oper_dept_id, String dept_id_after, String post_id_before,
			String post_id_after, String change_type, String post_name_after, String contact_num) {
		super(JobTypeEnum.USERSECLV_CHANGE, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.change_user_iidd = change_user_iidd;
		this.change_user_name = change_user_name;
		this.change_dept_id = change_dept_id;
		this.change_dept_name = change_dept_name;
		this.seclv_code_before = seclv_code_before;
		this.seclv_code_after = seclv_code_after;
		this.oper_user_iidd = oper_user_iidd;
		this.oper_dept_id = oper_dept_id;
		this.dept_id_after = dept_id_after;
		this.post_id_before = post_id_before;
		this.post_id_after = post_id_after;
		this.change_type = change_type;
		this.post_name_after = post_name_after;
		this.contact_num = contact_num;
	}
}
