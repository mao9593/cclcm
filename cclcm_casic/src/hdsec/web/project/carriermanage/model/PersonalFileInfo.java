package hdsec.web.project.carriermanage.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 添加个人涉密资料类
 * 
 * @author LS
 * 
 */
public class PersonalFileInfo extends BaseDomain {
	private String event_code = "";// 作业号
	private String duty_user_id = ""; // 责任人ID
	private String duty_dept_id = ""; // 责任部门ID
	private String duty_entp_id = ""; // 责任单位ID
	private String file_name = "";// 资料名称
	private Integer file_seclv_code = null;// 资料密级
	private String file_seclv_name = "";// 资料密级名称
	private String file_type = null;// 资料类型
	private String file_category = null;// 资料格式
	private String other_type = "";// 其他类型（用户手填）
	private Integer file_quantity = null;// 文件数量
	private String file_year = "";// 文件年份
	private String file_quarter = "";// 文件季度
	private Integer approve_status = 0;// 审批状态标志位:0未审批 1：已通过
	private String duty_user_name = ""; // 责任人
	private String duty_dept_name = ""; // 责任部门
	private String duty_entp_name = ""; // 责任单位
	private String job_code = "";// 任务号
	private String summ = "";// 备注

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
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

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
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

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Integer getFile_seclv_code() {
		return file_seclv_code;
	}

	public void setFile_seclv_code(Integer file_seclv_code) {
		this.file_seclv_code = file_seclv_code;
	}

	public String getFile_seclv_name() {
		return file_seclv_name;
	}

	public void setFile_seclv_name(String file_seclv_name) {
		this.file_seclv_name = file_seclv_name;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getFile_category() {
		return file_category;
	}

	public void setFile_category(String file_category) {
		this.file_category = file_category;
	}

	public String getOther_type() {
		return other_type;
	}

	public void setOther_type(String other_type) {
		this.other_type = other_type;
	}

	public Integer getFile_quantity() {
		return file_quantity;
	}

	public void setFile_quantity(Integer file_quantity) {
		this.file_quantity = file_quantity;
	}

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

	public Integer getApprove_status() {
		return approve_status;
	}

	public void setApprove_status(Integer approve_status) {
		this.approve_status = approve_status;
	}

	public PersonalFileInfo() {

	}

	public PersonalFileInfo(String file_name, Integer file_seclv_code, String file_type, String file_category,
			String other_type, Integer file_quantity, String event_code, String duty_user_id, String duty_dept_id,
			String duty_entp_id, String file_year, String file_quarter, Integer approve_status) {
		/* , String job_code */
		this.file_name = file_name;
		this.file_seclv_code = file_seclv_code;
		this.file_type = file_type;
		this.file_category = file_category;
		this.other_type = other_type;
		this.file_quantity = file_quantity;
		this.file_quarter = file_quarter;
		this.file_year = file_year;
		this.event_code = event_code;
		this.approve_status = approve_status;
		this.duty_user_id = duty_user_id;
		this.duty_dept_id = duty_dept_id;
		this.duty_entp_id = duty_entp_id;

		// this.job_code = job_code;
	}
}
