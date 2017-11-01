package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.passport.model.EntityPassport;
import hdsec.web.project.securityuser.model.AbroadInfo;
import hdsec.web.project.securityuser.model.UserAbroadEvent;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.UserSecurity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加用户因私出国申请
 * 
 * @author gj
 * 
 */
public class AddUserAbroadEventAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = ""; // 备注
	private String termini = "";// 目的地或途经国家
	private String journey = "";// 行程说明
	private String reason = ""; // 事由
	private Date go_time = null; // 活动开始时间
	private Date back_time = null; // 活动结束时间
	private String next_approver = "";// 下级审批人
	private List<UserAbroadEvent> eventList = null;
	private final String jobType = JobTypeEnum.SECUSER_ABROAD.getJobTypeCode();
	private BmRealUser bmUser = null;
	private String his_abroad = "";// 历史处境情况
	private Integer history_all = null;// 历史处境情况
	private String signname = "";
	private Date sign_time = null;
	private List<EntityPassport> userpassport = null;
	private String other_abroad = "";// 个人信息中出国情况次数统计
	private String leave = "";// 请假情况

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public List<EntityPassport> getUserpassport() {
		return userpassport;
	}

	public String getSignname() {
		return signname;
	}

	public void setSignname(String signname) {
		this.signname = signname;
	}

	public Date getSign_time() {
		return sign_time;
	}

	public void setSign_time(Date sign_time) {
		this.sign_time = sign_time;
	}

	public Integer getHistory_all() {
		return history_all;
	}

	public String getHis_abroad() {
		return his_abroad;
	}

	public BmRealUser getBmUser() {
		return bmUser;
	}

	public String getTermini() {
		return termini;
	}

	public void setTermini(String termini) {
		this.termini = termini;
	}

	public String getJourney() {
		return journey;
	}

	public void setJourney(String journey) {
		this.journey = journey;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getJobType() {
		return jobType;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getProject_code() {
		return project_code;
	}

	public List<UserAbroadEvent> getEventList() {
		return eventList;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
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
			UserAbroadEvent event = new UserAbroadEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
					project_code, termini, journey, go_time, back_time, reason, summ, signname, sign_time);
			event.setJobType(JobTypeEnum.valueOf(jobType));
			event.setLeave_name(leave);
			securityUserService.addUserAbroadEvent(event, next_approver);
			insertCommonLog("添加用户因私出国申请作业[" + event_code + "]");
			return "ok";
		} else {
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("real_user_id", getCurUser().getUser_iidd());
			mm.put("info_type", 2);
			List<BmRealUser> usertemp = securityUserService.getUserInfoByUserIdAndInfoType(mm);
			if (usertemp.size() > 0) {
				bmUser = usertemp.get(0);
			} else {
				bmUser = null;
			}
			// 查询个人信息中是否有出国情况，若有查询出国次数
			String abroadnum = "0";
			if (bmUser != null) {
				if (bmUser.getIs_abroad() == 1) {
					mm.put("event_code", bmUser.getEvent_code());
					List<AbroadInfo> aboradinfo = securityUserService.getUserAbroad(mm);
					if (aboradinfo != null) {
						abroadnum = aboradinfo.get(0).getRow_num();
					}
				}
			}

			// 查询该用户在该系统申请出国次数（已通过）+个人信息已出国次数和本年度出国次数（已通过）
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_id", getCurUser().getUser_iidd());
			String history = securityUserService.getUserHistoryAbroadInfo(map);
			history_all = Integer.valueOf(history).intValue() + Integer.valueOf(abroadnum).intValue();
			map.put("year", "now");
			his_abroad = securityUserService.getUserHistoryAbroadInfo(map);

			// 用户护照信息查询并展示
			map.put("duty_user_iidd", getCurUser().getUser_iidd());
			userpassport = passportService.getAllPassportList(map);
			event_code = getCurUser().getUser_iidd() + "_SECUSER_ABROAD_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
