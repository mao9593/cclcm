package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.secmanage.model.FileOutMakeEvent;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 涉密文件（资料）外出制作申请
 * 
 * @author gaoximin 2015-7-23
 */
public class AddFileOutMakeEventAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String kind = ""; // 申请种类（0印刷，1胶装，2特殊制作，3其他）多选时用|分隔
	private String file_name = ""; // 文件（资料）名称
	private Integer file_count = null; // 装订份数
	private Integer file_page = null; // 每份页数
	private Integer file_seclv_code = null; // 文件密级
	private String reason = ""; // 申请理由
	private String accompany_iidd = ""; // 陪同人员ID
	private String accompany_name = ""; // 陪同人员
	private String make_company = ""; // 制作单位

	private String next_approver = "";// 下级审批人
	private List<FileOutMakeEvent> eventList = null;
	private final String jobType = JobTypeEnum.FILEOUTMAKE.getJobTypeCode();

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

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		if (StringUtils.hasLength(kind)) {
			this.kind = Constants.COMMON_SEPARATOR
					+ kind.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR) + Constants.COMMON_SEPARATOR;
		}
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Integer getFile_count() {
		return file_count;
	}

	public void setFile_count(Integer file_count) {
		this.file_count = file_count;
	}

	public Integer getFile_page() {
		return file_page;
	}

	public void setFile_page(Integer file_page) {
		this.file_page = file_page;
	}

	public Integer getFile_seclv_code() {
		return file_seclv_code;
	}

	public void setFile_seclv_code(Integer file_seclv_code) {
		this.file_seclv_code = file_seclv_code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAccompany_iidd() {
		return accompany_iidd;
	}

	public void setAccompany_iidd(String accompany_iidd) {
		this.accompany_iidd = accompany_iidd;
	}

	public String getAccompany_name() {
		return accompany_name;
	}

	public void setAccompany_name(String accompany_name) {
		this.accompany_name = accompany_name;
	}

	public String getMake_company() {
		return make_company;
	}

	public void setMake_company(String make_company) {
		this.make_company = make_company;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<FileOutMakeEvent> getEventList() {
		return eventList;
	}

	public String getJobType() {
		return jobType;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	/*
	 * public List<SysUsage> getUsageList() { return basicService.getSysUsageList(); }
	 * 
	 * public List<SysProject> getProjectList() { return basicService.getSysProjectList(); }
	 * 
	 * public List<UserSecurity> getSecurityList() { return userService.getSecurityList(); }
	 */
	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			SecUser accUser = userService.getSecUserByUid(accompany_iidd);
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			FileOutMakeEvent event = new FileOutMakeEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
					project_code, summ, kind, file_name, file_count, file_page, file_seclv_code, reason,
					accompany_iidd, accUser.getUser_name(), make_company);
			event.setJobType(JobTypeEnum.valueOf(jobType));
			secManageService.addFileOutMakeEvent(event, next_approver);
			insertCommonLog("添加涉密文件资料外出制作申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_FILEOUTMAKE_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
