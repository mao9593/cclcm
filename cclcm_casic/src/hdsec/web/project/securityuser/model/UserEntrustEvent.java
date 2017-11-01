package hdsec.web.project.securityuser.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 用户委托保密管理类
 * 
 * @author gj
 * 
 */

public class UserEntrustEvent extends BaseEvent {
	private String entrust_user_iidd = "";// 被委托人ID
	private String entrust_user_name = "";// 被委托人名单
	private String entrust_dept_id = ""; // 被委托人部门ID
	private String entrust_dept_name = "";// 被委托人部门
	private String duty_name = ""; // 职务/职称
	private String passport_info = ""; // 护照信息
	private String be_dept_id = ""; // 委托管理部门ID
	private String be_dept_name = ""; // 委托管理部门
	private String confirm_info = ""; // 卸载载体信息
	private Date go_time = null; // 委托时间
	private Date back_time = null; // 派回时间
	private Integer entrust_status = 0; // 委托状态
	private String his_job_code = ""; // 包含该作业的历史任务列表

	public String getEntrust_status_name() {
		String name = "";
		switch (this.entrust_status) {
		case 0:
			name = "未委托";
			break;
		case 1:
			name = "已委托";
			break;
		case 2:
			name = "已派回";
			break;
		default:
			name = "";
			break;
		}
		return name;
	}

	public String getEntrust_user_iidd() {
		return entrust_user_iidd;
	}

	public void setEntrust_user_iidd(String entrust_user_iidd) {
		this.entrust_user_iidd = entrust_user_iidd;
	}

	public String getEntrust_user_name() {
		return entrust_user_name;
	}

	public void setEntrust_user_name(String entrust_user_name) {
		this.entrust_user_name = entrust_user_name;
	}

	public String getEntrust_dept_id() {
		return entrust_dept_id;
	}

	public void setEntrust_dept_id(String entrust_dept_id) {
		this.entrust_dept_id = entrust_dept_id;
	}

	public String getEntrust_dept_name() {
		return entrust_dept_name;
	}

	public void setEntrust_dept_name(String entrust_dept_name) {
		this.entrust_dept_name = entrust_dept_name;
	}

	public String getDuty_name() {
		return duty_name;
	}

	public void setDuty_name(String duty_name) {
		this.duty_name = duty_name;
	}

	public String getPassport_info() {
		return passport_info;
	}

	public void setPassport_info(String passport_info) {
		this.passport_info = passport_info;
	}

	public String getBe_dept_id() {
		return be_dept_id;
	}

	public void setBe_dept_id(String be_dept_id) {
		this.be_dept_id = be_dept_id;
	}

	public String getBe_dept_name() {
		return be_dept_name;
	}

	public void setBe_dept_name(String be_dept_name) {
		this.be_dept_name = be_dept_name;
	}

	public String getConfirm_info() {
		return confirm_info;
	}

	public void setConfirm_info(String confirm_info) {
		this.confirm_info = confirm_info;
	}

	public Date getGo_time() {
		return go_time;
	}

	public void setGo_time(Date go_time) {
		this.go_time = go_time;
	}

	public Date getBack_time() {
		return back_time;
	}

	public void setBack_time(Date back_time) {
		this.back_time = back_time;
	}

	public Integer getEntrust_status() {
		return entrust_status;
	}

	public void setEntrust_status(Integer entrust_status) {
		this.entrust_status = entrust_status;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public UserEntrustEvent() {
		super(JobTypeEnum.SECUSER_ENTRUST);
	}

	public UserEntrustEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String entrust_user_iidd, String entrust_user_name,
			String entrust_dept_id, String entrust_dept_name, String duty_name, String passport_info,
			String be_dept_id, String be_dept_name, String confirm_info, Date go_time, Date back_time) {
		super(JobTypeEnum.SECUSER_ENTRUST, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.entrust_user_iidd = entrust_user_iidd;
		this.entrust_user_name = entrust_user_name;
		this.entrust_dept_id = entrust_dept_id;
		this.entrust_dept_name = entrust_dept_name;
		this.duty_name = duty_name;
		this.passport_info = passport_info;
		this.be_dept_id = be_dept_id;
		this.be_dept_name = be_dept_name;
		this.confirm_info = confirm_info;
		this.go_time = go_time;
		this.back_time = back_time;
	}
}