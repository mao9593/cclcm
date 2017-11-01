package hdsec.web.project.securityuser.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 用户个人信息完善类
 * 
 * @author guojiao
 * 
 */

public class BmUserInfoEvent extends BaseEvent {
	private Date confirm_time = null; // 变更时间
	private String confirm_user_iidd = "";// 确认人ID
	private String confirm_user_name = "";// 确认人
	private String his_job_code = ""; // 包含该作业的历史任务列表
	private Integer file_num = null;// 附件数量
	private String file_list = "";// 附件名称

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public String getConfirm_time() {
		return confirm_time == null ? "" : getSdf().format(confirm_time);
	}

	public void setConfirm_time(Date confirm_time) {
		this.confirm_time = confirm_time;
	}

	public String getConfirm_user_iidd() {
		return confirm_user_iidd;
	}

	public void setConfirm_user_iidd(String confirm_user_iidd) {
		this.confirm_user_iidd = confirm_user_iidd;
	}

	public String getConfirm_user_name() {
		return confirm_user_name;
	}

	public void setConfirm_user_name(String confirm_user_name) {
		this.confirm_user_name = confirm_user_name;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public BmUserInfoEvent() {
		super(JobTypeEnum.USER_INFO);
	}

	public BmUserInfoEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String confirm_user_iidd, String confirm_user_name) {
		super(JobTypeEnum.USER_INFO, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.confirm_user_iidd = confirm_user_iidd;
		this.confirm_user_name = confirm_user_name;

	}

}
