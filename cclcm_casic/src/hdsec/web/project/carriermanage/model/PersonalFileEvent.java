package hdsec.web.project.carriermanage.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * 添加涉密人员个人涉密资料类
 * 
 * @author LS
 * 
 */
public class PersonalFileEvent extends BaseEvent {
	private String duty_user_id = ""; // 责任人编号
	private String duty_user_name = ""; // 责任人
	private String duty_dept_id = ""; // 责任部门编号
	private String duty_dept_name = ""; // 责任部门
	private String duty_entp_id = ""; // 责任单位编号
	private String duty_entp_name = ""; // 责任单位
	private Integer file_num = null;// 上传报道个数
	private String file_year = "";// 文件年份
	private String file_quarter = "";// 文件季度

	public String getFile_year() {
		return file_year;
	}

	public void setFile_year(String file_year) {
		this.file_year = file_year;
	}

	public String getFile_quarter() {
		return file_quarter;
	}

	public void setFile_quarter(String file_quarter) {
		this.file_quarter = file_quarter;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getDuty_entp_name() {
		return duty_entp_name;
	}

	public void setDuty_entp_name(String duty_entp_name) {
		this.duty_entp_name = duty_entp_name;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_entp_id() {
		return duty_entp_id;
	}

	public void setDuty_entp_id(String duty_entp_id) {
		this.duty_entp_id = duty_entp_id;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public PersonalFileEvent() {
		super(JobTypeEnum.PERSONAL_FILE);
	}

	public PersonalFileEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code,
			String usage_code, String project_code, String summ, String duty_user_id, Integer file_num,
			String duty_dept_id, String duty_entp_id, String file_year, String file_quarter) {
		super(JobTypeEnum.PERSONAL_FILE, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.duty_user_id = duty_user_id;
		this.file_num = file_num;
		this.duty_dept_id = duty_dept_id;
		this.duty_entp_id = duty_entp_id;
		this.file_year = file_year;
		this.file_quarter = file_quarter;
	}
}