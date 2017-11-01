package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.securityuser.model.ResignEvent;
import hdsec.web.project.securityuser.model.SysUserPost;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.UserSecurity;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加用户离职申请
 * 
 * @author yangjl
 * 
 */
public class AddResignEventAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_iidd = ""; // 离职人ID
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String resign_user_iidd = ""; // 离职人ID
	private String resign_user_name = ""; // 离职人
	private String resign_dept_name = ""; // 离职人部门
	private String resign_dept_id = ""; // 离职人部门ID
	private String next_approver = "";// 下级审批人
	private Date apply_time = null;// 申请时间
	private String reason = "";// 离职原因
	private String resign_others = "";// 离职原因（其他）
	private String dept_id_after = ""; // 变更后部门、定部门
	private String dept_name_after = ""; // 变更后部门、定部门
	private String post_id_after = ""; // 变更后岗位、定岗
	private String post_name_after = ""; // 变更后岗位、定岗
	// private Date start_time = null;//脱密期开始时间
	// private Date end_time = null;//脱密期结束时间

	private String summ = "";// 备注说明
	private List<ResignEvent> eventList = null;
	private final String jobType = JobTypeEnum.RESIGN.getJobTypeCode();
	private Date sign_time = null;
	private String signname = "";
	private String post_id_before = ""; // 变更前岗位id

	public String getPost_id_before() {
		return post_id_before;
	}

	public void setPost_id_before(String post_id_before) {
		this.post_id_before = post_id_before;
	}

	public Date getSign_time() {
		return sign_time;
	}

	public void setSign_time(Date sign_time) {
		this.sign_time = sign_time;
	}

	public String getSignname() {
		return signname;
	}

	public void setSignname(String signname) {
		this.signname = signname;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_iidd() {
		return user_iidd;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getResign_user_iidd() {
		return resign_user_iidd;
	}

	public void setResign_user_iidd(String resign_user_iidd) {
		this.resign_user_iidd = resign_user_iidd;
	}

	public String getResign_user_name() {
		return resign_user_name;
	}

	public void setResign_user_name(String resign_user_name) {
		this.resign_user_name = resign_user_name;
	}

	public List<ResignEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<ResignEvent> eventList) {
		this.eventList = eventList;
	}

	public String getResign_dept_name() {
		return resign_dept_name;
	}

	public void setResign_dept_name(String resign_dept_name) {
		this.resign_dept_name = resign_dept_name;
	}

	public String getResign_dept_id() {
		return resign_dept_id;
	}

	public void setResign_dept_id(String resign_dept_id) {
		this.resign_dept_id = resign_dept_id;
	}

	public String getDept_id_after() {
		return dept_id_after;
	}

	public void setDept_id_after(String dept_id_after) {
		this.dept_id_after = dept_id_after;
	}

	public String getDept_name_after() {
		return dept_name_after;
	}

	public void setDept_name_after(String dept_name_after) {
		this.dept_name_after = dept_name_after;
	}

	public String getPost_id_after() {
		return post_id_after;
	}

	public void setPost_id_after(String post_id_after) {
		this.post_id_after = post_id_after;
	}

	public String getPost_name_after() {
		return post_name_after;
	}

	public void setPost_name_after(String post_name_after) {
		this.post_name_after = post_name_after;
	}

	public String getResign_others() {
		return resign_others;
	}

	public void setResign_others(String resign_others) {
		if (resign_others.trim().substring(0, 1).equals(",")) {
			// 去掉开头的逗号
			this.resign_others = resign_others.trim().substring(1).trim();
		} else if (resign_others.trim().substring(resign_others.trim().length() - 1).equals(",")) {
			// 去掉结尾的逗号
			this.resign_others = resign_others.trim().substring(0, resign_others.trim().length() - 1);

		} else {
			this.resign_others = resign_others.trim();
		}
	}

	// public Date getStart_time() {
	// return start_time;
	// }

	// public void setStart_time(Date start_time) {
	// this.start_time = start_time;
	// }

	// public Date getEnd_time() {
	// return end_time;
	// }

	// public void setEnd_time(Date end_time) {
	// this.end_time = end_time;
	// }

	public List<SysUserPost> getPostList() {
		return securityUserService.getPostList();
	}

	public List<SysUserPost> getDeptList() {
		return securityUserService.getDeptList();
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

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getJobType() {
		return jobType;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			// 查询离职用户信息
			SecUser resignUser = userService.getSecUserByUid(resign_user_iidd);
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			ResignEvent event = new ResignEvent(user_iidd, dept_id, event_code, seclv_code, usage_code, project_code,
					reason, summ, resign_user_iidd, resignUser.getUser_name(), resignUser.getDept_id(),
					resignUser.getDept_name(), dept_id_after, dept_name_after, post_id_after, post_name_after,
					resign_others, signname, sign_time, resignUser.getPost_id());
			event.setJobType(JobTypeEnum.valueOf(jobType));
			securityUserService.addResignEvent(event, next_approver);
			insertCommonLog("添加用户离职申请作业[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_USERRESIGN_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
