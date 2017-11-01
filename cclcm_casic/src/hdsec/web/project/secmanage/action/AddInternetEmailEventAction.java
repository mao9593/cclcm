package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.secmanage.model.FileListEvent;
import hdsec.web.project.secmanage.model.InternetEmailEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 外网电子邮件申请
 * 
 * @author guojiao
 */
public class AddInternetEmailEventAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
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
	private FileListEvent file = null;

	private String next_approver = "";// 下级审批人
	private List<InternetEmailEvent> eventList = null;
	private final String jobType = JobTypeEnum.INTER_EMAIL.getJobTypeCode();

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getReply_email() {
		return reply_email;
	}

	public void setReply_email(String reply_email) {
		this.reply_email = reply_email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
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

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<InternetEmailEvent> getEventList() {
		return eventList;
	}

	public String getJobType() {
		return jobType;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();

			// 使用定义上传附件类
			file = new FileListEvent();
			handleFileList(event_code, file, "");

			InternetEmailEvent event = new InternetEmailEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
					project_code, summ, duty_user_id, duty_user_name, duty_dept_id, duty_dept_name, contact_num,
					receive_org, send_address, cc_addrress, title, body, file.getFile_num(), file.getFile_list(),
					reply_email);

			event.setJobType(JobTypeEnum.valueOf(jobType));
			secManageService.addInternetEmailEvent(event, next_approver);
			insertCommonLog("添加外网电子邮件申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_INTERNETEMAIL_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
