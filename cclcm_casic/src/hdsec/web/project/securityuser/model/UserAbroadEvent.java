package hdsec.web.project.securityuser.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 涉密人员因私出国
 * 
 * @author gj
 * 
 */

public class UserAbroadEvent extends BaseEvent {
	private String termini = ""; // 目的地或途经国家
	private String journey = ""; // 行程说明
	private Date go_time = null; // 计划外出时间
	private Date back_time = null; // 计划返回时间
	private String reason = ""; // 事由
	private Integer abroad_status = 0; // 状态(是否通过审批，该字段仅为统计历史出境情况使用gj)
	private String his_job_code = ""; // 包含该作业的历史任务列表
	private Integer approve_file_num = null;// 附件数量
	private String approve_file_list = "";// 附件名称
	private String signname = "";
	private Date sign_time = null;
	private String leave_name = "";

	public String getLeave_name() {
		return leave_name;
	}

	public void setLeave_name(String leave_name) {
		this.leave_name = leave_name;
	}

	public String getSignname() {
		return signname;
	}

	public void setSignname(String signname) {
		this.signname = signname;
	}

	public String getSign_time() {
		return sign_time == null ? "" : getSdf().format(sign_time);
	}

	public void setSign_time(Date sign_time) {
		this.sign_time = sign_time;
	}

	public Integer getApprove_file_num() {
		return approve_file_num;
	}

	public void setApprove_file_num(Integer approve_file_num) {
		this.approve_file_num = approve_file_num;
	}

	public String getApprove_file_list() {
		return approve_file_list;
	}

	public void setApprove_file_list(String approve_file_list) {
		this.approve_file_list = approve_file_list;
	}

	public String getTermini() {
		return termini;
	}

	public void setTermini(String termini) {
		this.termini = termini;
	}

	public String getJourney() {
		return journey;
	}

	public void setJourney(String journey) {
		this.journey = journey;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getAbroad_status() {
		return abroad_status;
	}

	public void setAbroad_status(Integer abroad_status) {
		this.abroad_status = abroad_status;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getAbroad_status_name() {
		String name = "";
		switch (this.abroad_status) {
		case 0:
			name = "已申请";
			break;
		case 1:
			name = "审批通过";
			break;
		case 2:
			name = "已返回";
			break;
		default:
			name = "";
			break;
		}
		return name;
	}

	public String getGo_time_str() {
		return go_time == null ? "" : getSdf().format(go_time);
	}

	public String getBack_time_str() {
		return back_time == null ? "" : getSdf().format(back_time);
	}

	public UserAbroadEvent() {
		super(JobTypeEnum.SECUSER_ABROAD);
	}

	public UserAbroadEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String termini, String journey, Date go_time, Date back_time, String reason,
			String summ, String signname, Date sign_time) {
		super(JobTypeEnum.SECUSER_ABROAD, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.termini = termini;
		this.journey = journey;
		this.go_time = go_time;
		this.back_time = back_time;
		this.reason = reason;
		this.signname = signname;
		this.sign_time = sign_time;
	}
}
