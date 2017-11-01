package hdsec.web.project.secmanage.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * 外网电子邮件作业
 * 
 * @author guojiao
 */
public class InternetEmailEvent extends BaseEvent {

	private String contact_num = ""; // 联系电话
	private String duty_user_id = "";// 责任人ID
	private String duty_user_name = "";// 责任人
	private String duty_dept_id = "";// 责任人部门ID
	private String duty_dept_name = "";// 责任人部门
	private String receive_org = "";// 收件单位
	private String send_address = "";// 主送（邮件地址）
	private String cc_addrress = "";// 抄送（邮件地址）
	private String reply_email = "";// 回复邮件
	private String title = "";// 主题
	private String body = "";// 正文
	private Integer file_num = null;// 附件数量
	private String file_list = "";// 附件名称

	public String getReply_email() {
		return reply_email;
	}

	public void setReply_email(String reply_email) {
		this.reply_email = reply_email;
	}

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
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

	public String getReceive_org() {
		return receive_org;
	}

	public void setReceive_org(String receive_org) {
		this.receive_org = receive_org;
	}

	public String getSend_address() {
		return send_address;
	}

	public void setSend_address(String send_address) {
		this.send_address = send_address;
	}

	public String getCc_addrress() {
		return cc_addrress;
	}

	public void setCc_addrress(String cc_addrress) {
		this.cc_addrress = cc_addrress;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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

	public InternetEmailEvent() {
		super(JobTypeEnum.INTER_EMAIL);
	}

	public InternetEmailEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code,
			String usage_code, String project_code, String summ, String duty_user_id, String duty_user_name,
			String duty_dept_id, String duty_dept_name, String contact_num, String receive_org, String send_address,
			String cc_addrress, String title, String body, Integer file_num, String file_list, String reply_email) {
		super(JobTypeEnum.INTER_EMAIL, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.duty_dept_id = duty_dept_id;
		this.duty_dept_name = duty_dept_name;
		this.duty_user_id = duty_user_id;
		this.duty_user_name = duty_user_name;
		this.contact_num = contact_num;
		this.receive_org = receive_org;
		this.send_address = send_address;
		this.cc_addrress = cc_addrress;
		this.title = title;
		this.body = body;
		this.file_num = file_num;
		this.file_list = file_list;
		this.reply_email = reply_email;
	}
}
