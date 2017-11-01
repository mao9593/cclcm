package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.secmanage.model.ExchangeMaterialEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 外网电子邮件申请
 * 
 * @author guojiao
 */
public class AddExchangeMaterialEventAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String contact_num = ""; // 联系电话
	private String exc_object = "";// 交流对象
	private String exc_location = "";// 交流地点
	private Integer exc_type = null;// 交流材料类型
	private String file_list = "";// 附件名称

	private String next_approver = "";// 下级审批人
	private List<ExchangeMaterialEvent> eventList = null;
	private final String jobType = JobTypeEnum.MATERIAL.getJobTypeCode();

	private String file_name = "";
	private String file_selv = "";
	private String selv_name = "";
	private Integer file_num = null;
	private int i = 0;
	private String[] filename = null;
	private String[] fileselv = null;

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getFile_selv() {
		return file_selv;
	}

	public void setFile_selv(String file_selv) {
		this.file_selv = file_selv;
	}

	public String getSelv_name() {
		return selv_name;
	}

	public void setSelv_name(String selv_name) {
		this.selv_name = selv_name;
	}

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

	public String getExc_object() {
		return exc_object;
	}

	public void setExc_object(String exc_object) {
		this.exc_object = exc_object;
	}

	public String getExc_location() {
		return exc_location;
	}

	public void setExc_location(String exc_location) {
		this.exc_location = exc_location;
	}

	public Integer getExc_type() {
		return exc_type;
	}

	public void setExc_type(Integer exc_type) {
		this.exc_type = exc_type;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<ExchangeMaterialEvent> getEventList() {
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

			if (!file_name.equals("")) {
				filename = file_name.split(",");
			}
			if (!file_selv.equals("")) {
				fileselv = file_selv.split(",");
			}

			// 交流材料：|材料名称|密级|end|#
			for (i = 0; i < file_num; i++) {
				file_list = file_list + Constants.COMMON_SEPARATOR + filename[i].trim() + Constants.COMMON_SEPARATOR
						+ fileselv[i].trim() + Constants.COMMON_SEPARATOR + "end" + Constants.COMMON_SEPARATOR + "#";
			}
			ExchangeMaterialEvent event = new ExchangeMaterialEvent(user_iidd, dept_id, event_code, seclv_code,
					usage_code, project_code, summ, contact_num, file_list, exc_object, exc_location, exc_type);

			event.setJobType(JobTypeEnum.valueOf(jobType));
			secManageService.addExchangeMaterialEvent(event, next_approver);
			insertCommonLog("添加对外交流材料申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_MATERIAL_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
