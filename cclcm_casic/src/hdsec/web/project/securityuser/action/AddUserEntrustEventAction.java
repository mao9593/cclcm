package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.securityuser.model.UserEntrustEvent;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.UserSecurity;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加用户委托保密管理申请
 * 
 * @author gj
 * 
 */
public class AddUserEntrustEventAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String entrust_user_iidd = "";// 被委托人ID
	private String entrust_user_name = "";// 被委托人名单
	private String duty_name = ""; // 职务/职称
	private String passport_info = ""; // 护照信息
	private String be_dept_id = ""; // 委托管理部门ID
	private String be_dept_name = ""; // 委托管理部门
	private String confirm_info = ""; // 卸载载体信息
	private Date go_time = null; // 委托时间
	private Date back_time = null; // 派回时间
	private String next_approver = "";// 下级审批人
	private List<UserEntrustEvent> eventList = null;
	private final String jobType = JobTypeEnum.SECUSER_ENTRUST.getJobTypeCode();

	public String getEntrust_user_iidd() {
		return entrust_user_iidd;
	}

	public void setEntrust_user_iidd(String entrust_user_iidd) {
		this.entrust_user_iidd = entrust_user_iidd;
	}

	public String getEntrust_user_name() {
		return entrust_user_name;
	}

	public void setEntrust_user_name(String entrust_user_name) {
		this.entrust_user_name = entrust_user_name;
	}

	public String getDuty_name() {
		return duty_name;
	}

	public void setDuty_name(String duty_name) {
		this.duty_name = duty_name;
	}

	public String getPassport_info() {
		return passport_info;
	}

	public void setPassport_info(String passport_info) {
		this.passport_info = passport_info;
	}

	public String getBe_dept_id() {
		return be_dept_id;
	}

	public void setBe_dept_id(String be_dept_id) {
		this.be_dept_id = be_dept_id;
	}

	public String getBe_dept_name() {
		return be_dept_name;
	}

	public void setBe_dept_name(String be_dept_name) {
		this.be_dept_name = be_dept_name;
	}

	public String getConfirm_info() {
		return confirm_info;
	}

	public void setConfirm_info(String confirm_info) {
		this.confirm_info = confirm_info;
	}

	public Date getGo_time() {
		return go_time;
	}

	public void setGo_time(Date go_time) {
		this.go_time = go_time;
	}

	public Date getBack_time() {
		return back_time;
	}

	public void setBack_time(Date back_time) {
		this.back_time = back_time;
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

	public List<UserEntrustEvent> getEventList() {
		return eventList;
	}

	public String getJobType() {
		return jobType;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();

			SecUser EntrustUser = userService.getSecUserByUid(entrust_user_iidd);
			String entrust_dept_id = EntrustUser.getDept_id();
			String entrust_dept_name = EntrustUser.getDept_name();
			UserEntrustEvent event = new UserEntrustEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
					project_code, summ, entrust_user_iidd, entrust_user_name, entrust_dept_id, entrust_dept_name,
					duty_name, passport_info, be_dept_id, be_dept_name, confirm_info, go_time, back_time);
			event.setJobType(JobTypeEnum.valueOf(jobType));
			securityUserService.addUserEntrustEvent(event, next_approver);
			insertCommonLog("添加用户委托保密管理申请作业[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_SECENTRUST_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
