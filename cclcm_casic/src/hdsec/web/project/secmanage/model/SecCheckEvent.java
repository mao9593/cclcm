package hdsec.web.project.secmanage.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * 部门专项保密检查
 * 
 * @author guojiao
 */
public class SecCheckEvent extends BaseEvent {

	private String contact_num = ""; // 联系电话
	private String check_content = "";// 保密检查说明
	private Integer file_num = null;// 附件数量
	private String file_list = "";// 附件名称
	private Integer approve_file_num = null;// 附件数量
	private String approve_file_list = "";// 附件名称

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

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public String getCheck_content() {
		return check_content;
	}

	public void setCheck_content(String check_content) {
		this.check_content = check_content;
	}

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

	public SecCheckEvent() {
		super(JobTypeEnum.SEC_CHECK);
	}

	public SecCheckEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code, String usage_code,
			String project_code, String summ, String contact_num, Integer file_num, String file_list,
			String check_content) {
		super(JobTypeEnum.SEC_CHECK, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.contact_num = contact_num;
		this.file_num = file_num;
		this.file_list = file_list;
		this.check_content = check_content;
	}
}
