package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.secmanage.model.FileListEvent;
import hdsec.web.project.secmanage.model.SecCheckEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 部门专项保密检查申请
 * 
 * @author guojiao
 */
public class AddSecCheckEventAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String contact_num = ""; // 联系电话
	private String check_content = "";// 保密检查说明
	private FileListEvent file = null;

	private String next_approver = "";// 下级审批人
	private List<SecCheckEvent> eventList = null;
	private final String jobType = JobTypeEnum.SEC_CHECK.getJobTypeCode();

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
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

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<SecCheckEvent> getEventList() {
		return eventList;
	}

	public String getJobType() {
		return jobType;
	}

	public String getCheck_content() {
		return check_content;
	}

	public void setCheck_content(String check_content) {
		this.check_content = check_content;
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

			SecCheckEvent event = new SecCheckEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
					project_code, summ, contact_num, file.getFile_num(), file.getFile_list(), check_content);

			event.setJobType(JobTypeEnum.valueOf(jobType));
			secManageService.addSecCheckEvent(event, next_approver);
			insertCommonLog("添加部门专项保密检查申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_SEC_CHECK_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
