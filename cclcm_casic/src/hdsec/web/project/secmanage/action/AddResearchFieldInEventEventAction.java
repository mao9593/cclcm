package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.secmanage.model.ResearchFieldInEvent;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 进入重要科研场地申请
 * 
 * @author gaoximin 2015-7-21
 */
public class AddResearchFieldInEventEventAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String contact_num = ""; // 联系电话
	private String visitor_company = ""; // 进入成员单位
	private String visitor_names = ""; // 进入成员名单
	private String field_site = ""; // 拟进入科研场地地点
	private String reason = ""; // 事由
	private String belongings = ""; // 携带物品
	private String rec_user_iidd = ""; // 接待人ID
	private String rec_user_name = ""; // 接待人
	private Date enter_time = null; // 进入时间
	private Date leave_time = null; // 离开时间

	private String next_approver = "";// 下级审批人
	private List<ResearchFieldInEvent> eventList = null;
	private final String jobType = JobTypeEnum.FIELDIN.getJobTypeCode();

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

	public String getVisitor_company() {
		return visitor_company;
	}

	public void setVisitor_company(String visitor_company) {
		this.visitor_company = visitor_company;
	}

	public String getVisitor_names() {
		return visitor_names;
	}

	public void setVisitor_names(String visitor_names) {
		this.visitor_names = visitor_names;
	}

	public String getField_site() {
		return field_site;
	}

	public void setField_site(String field_site) {
		this.field_site = field_site;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBelongings() {
		return belongings;
	}

	public void setBelongings(String belongings) {
		this.belongings = belongings;
	}

	public String getRec_user_iidd() {
		return rec_user_iidd;
	}

	public void setRec_user_iidd(String rec_user_iidd) {
		this.rec_user_iidd = rec_user_iidd;
	}

	public String getRec_user_name() {
		return rec_user_name;
	}

	public void setRec_user_name(String rec_user_name) {
		this.rec_user_name = rec_user_name;
	}

	public Date getEnter_time() {
		return enter_time;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public Date getLeave_time() {
		return leave_time;
	}

	public void setLeave_time(Date leave_time) {
		this.leave_time = leave_time;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<ResearchFieldInEvent> getEventList() {
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
			SecUser recUser = userService.getSecUserByUid(rec_user_iidd);
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			ResearchFieldInEvent event = new ResearchFieldInEvent(user_iidd, dept_id, event_code, seclv_code,
					usage_code, project_code, summ, contact_num, visitor_company, visitor_names, field_site, reason,
					belongings, rec_user_iidd, recUser.getUser_name(), enter_time, leave_time);

			event.setJobType(JobTypeEnum.valueOf(jobType));

			secManageService.addResearchFieldInEvent(event, next_approver);
			insertCommonLog("添加进入重要科研场地申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_RESEARCHFIELDIN_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
