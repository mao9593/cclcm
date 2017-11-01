package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.securityuser.model.SysUserPost;
import hdsec.web.project.securityuser.model.UserSeclvChangeEvent;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.UserSecurity;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加用户定密、涉密等级变更申请
 * 
 * @author gaoximin 2015-7-8
 */
public class AddUserSeclvChangeEventAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String change_user_iidd = ""; // 变更人ID
	private String change_user_name = ""; // 变更人
	private String seclv_code_after = ""; // 变更后密级、定密
	private String next_approver = "";// 下级审批人
	private List<UserSeclvChangeEvent> eventList = null;
	private final String jobType = JobTypeEnum.USERSECLV_CHANGE.getJobTypeCode();
	private final String jobType_add = JobTypeEnum.USERSECLV_ADD.getJobTypeCode();
	private String dept_id_after = ""; // 变更后部门、定部门
	private String post_id_after = ""; // 变更后岗位、定岗
	private String type = ""; // url传递的标志,NEW为新增，其他为变更
	private String change_type = ""; // 业务流程类型,ADD代表新增涉密人员，CHANGE代表涉密等级表更
	private String post_name_after = ""; // 变更后岗位
	private String contact_num = "";// 联系电话
	private SecUser secUser = null;

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public String getPost_name_after() {
		return post_name_after;
	}

	public void setPost_name_after(String post_name_after) {
		this.post_name_after = post_name_after;
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

	public String getChange_user_iidd() {
		return change_user_iidd;
	}

	public void setChange_user_iidd(String change_user_iidd) {
		this.change_user_iidd = change_user_iidd;
	}

	public String getChange_user_name() {
		return change_user_name;
	}

	public void setChange_user_name(String change_user_name) {
		this.change_user_name = change_user_name;
	}

	public String getSeclv_code_after() {
		return seclv_code_after;
	}

	public void setSeclv_code_after(String seclv_code_after) {
		this.seclv_code_after = seclv_code_after;
	}

	public List<UserSeclvChangeEvent> getEventList() {
		return eventList;
	}

	public String getJobType() {
		return jobType;
	}

	public String getJobType_add() {
		return jobType_add;
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

	public List<SysUserPost> getPostList() {
		return securityUserService.getPostList();
	}

	public String getDept_id_after() {
		return dept_id_after;
	}

	public void setDept_id_after(String dept_id_after) {
		this.dept_id_after = dept_id_after;
	}

	public String getPost_id_after() {
		return post_id_after;
	}

	public void setPost_id_after(String post_id_after) {
		this.post_id_after = post_id_after;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SecUser getSecUser() {
		return secUser;
	}

	public void setSecUser(SecUser secUser) {
		this.secUser = secUser;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			// 查询变更用户信息
			SecUser changeUser = userService.getSecUserByUid(change_user_iidd);
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			secUser = userService.getSecUserByUid(change_user_iidd);
			// 判断流程类型
			if (type.equals("NEW")) {
				change_type = "ADD";
			} else {
				change_type = "CHANGE";
			}
			UserSeclvChangeEvent event = new UserSeclvChangeEvent(user_iidd, dept_id, event_code, seclv_code,
					usage_code, project_code, summ, change_user_iidd, changeUser.getUser_name(),
					changeUser.getDept_id(), changeUser.getDept_name(), changeUser.getSecurity_code(),
					seclv_code_after, "", "", dept_id_after, changeUser.getPost_id(), post_id_after, change_type,
					post_name_after, contact_num);
			// 判断流程类型
			if (type.equals("NEW")) {
				event.setJobType(JobTypeEnum.valueOf(jobType_add));
				securityUserService.addUserSeclvChangeEvent(event, next_approver);
				insertCommonLog("添加新增涉密人员申请作业[" + event_code + "]");
				return "add";
			} else {
				event.setJobType(JobTypeEnum.valueOf(jobType));
				securityUserService.addUserSeclvChangeEvent(event, next_approver);
				insertCommonLog("添加涉密人员变更申请作业[" + event_code + "]");
				return "ok";
			}

		} else {
			event_code = getCurUser().getUser_iidd() + "_SECURITYUSER_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}

}
