package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.secmanage.model.PunishRectifyEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 保密工作违规处罚整改督查申请
 * 
 * @author gaoximin 2015-7-24
 */
public class AddPunishEventAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String company_code = ""; // （处罚）单位代码
	private String punish_dept_id = ""; // 处罚/整改部门ID
	private String punish_dept_name = ""; // 处罚/整改部门
	private String duty_user_iidd = ""; // 责任/办理人ID
	private String duty_user_name = ""; // 责任/办理人
	private String describe = ""; // 违规/不合规事项描述
	private String advise = ""; // 处罚建议
	private String rectify_demand = ""; // 整改要求
	private String rectify_according = ""; // 整改依据
	private Date rectify_time = null; // 完成整改时间
	private Integer apply_type = null; // 申请业务类型
	private String type = ""; // 参数

	private String next_approver = "";// 下级审批人
	private List<PunishRectifyEvent> eventList = null;
	private final String jobType_dept = JobTypeEnum.PUNISH_DEPT.getJobTypeCode();
	private final String jobType_seccheck = JobTypeEnum.PUNISH_SECCHECK.getJobTypeCode();
	private final String jobType_rectify = JobTypeEnum.PUNISH_RECTIFY.getJobTypeCode();
	private String jobType = "";
	private String func = "";
	private List<ApproverUser> userList = null;

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
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

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getPunish_dept_id() {
		return punish_dept_id;
	}

	public void setPunish_dept_id(String punish_dept_id) {
		this.punish_dept_id = punish_dept_id;
	}

	public String getPunish_dept_name() {
		return punish_dept_name;
	}

	public void setPunish_dept_name(String punish_dept_name) {
		this.punish_dept_name = punish_dept_name;
	}

	public String getDuty_user_iidd() {
		return duty_user_iidd;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getAdvise() {
		return advise;
	}

	public void setAdvise(String advise) {
		this.advise = advise;
	}

	public String getRectify_demand() {
		return rectify_demand;
	}

	public void setRectify_demand(String rectify_demand) {
		this.rectify_demand = rectify_demand;
	}

	public String getRectify_according() {
		return rectify_according;
	}

	public void setRectify_according(String rectify_according) {
		this.rectify_according = rectify_according;
	}

	public Date getRectify_time() {
		return rectify_time;
	}

	public void setRectify_time(Date rectify_time) {
		this.rectify_time = rectify_time;
	}

	public Integer getApply_type() {
		return apply_type;
	}

	public void setApply_type(Integer apply_type) {
		this.apply_type = apply_type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getJobType() {
		String name = "";
		if (type != null) {
			switch (type) {
			case "dept":
				name = JobTypeEnum.PUNISH_DEPT.getJobTypeCode();
				break;
			case "seccheck":
				name = JobTypeEnum.PUNISH_SECCHECK.getJobTypeCode();
				break;
			case "rectify":
				name = JobTypeEnum.PUNISH_RECTIFY.getJobTypeCode();
				break;
			default:
				name = "";
				break;
			}
		}
		return name;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobType_dept() {
		return jobType_dept;
	}

	public String getJobType_seccheck() {
		return jobType_seccheck;
	}

	public String getJobType_rectify() {
		return jobType_rectify;
	}

	public void setEventList(List<PunishRectifyEvent> eventList) {
		this.eventList = eventList;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<PunishRectifyEvent> getEventList() {
		return eventList;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			if (func.equals("NEXT")) {
				if (!punish_dept_id.equals("")) {
					userList = getFixApprover(punish_dept_id, "10");
					if (userList != null) {
						func = "ok";
					} else {
						func = "none";
					}
					return "choice";
				} else {
					return SUCCESS;
				}
			}
			if (!duty_user_iidd.isEmpty()) {
				SecUser dutyUser = userService.getSecUserByUid(duty_user_iidd);
				duty_user_name = dutyUser.getUser_name();
			}
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			// 判断申请类型
			String applytype = "";
			if (type.equals("dept")) {
				apply_type = 0;
				applytype = "部门自查违规处罚";
			} else if (type.equals("seccheck")) {
				apply_type = 1;
				applytype = "保密检查违规处罚";
			} else if (type.equals("rectify")) {
				apply_type = 2;
				applytype = "保密整改督查";
			}
			// 判断单位
			String company_name = "";
			if (company_code.equals("62")) {
				company_name = "声光电公司";
			} else if (company_code.equals("24")) {
				company_name = "24所";
			} else if (company_code.equals("26")) {
				company_name = "26所";
			} else if (company_code.equals("44")) {
				company_name = "44所";
			}

			PunishRectifyEvent event = new PunishRectifyEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
					project_code, summ, company_code, company_name, punish_dept_id, punish_dept_name, duty_user_iidd,
					duty_user_name, describe, advise, rectify_demand, rectify_according, rectify_time, apply_type);

			if (type.equals("dept")) {
				event.setJobType(JobTypeEnum.valueOf(jobType_dept));
			} else if (type.equals("seccheck")) {
				event.setJobType(JobTypeEnum.valueOf(jobType_seccheck));
			} else if (type.equals("rectify")) {
				event.setJobType(JobTypeEnum.valueOf(jobType_rectify));
			}
			secManageService.addPunishEvent(event, next_approver);
			insertCommonLog("添加" + applytype + "申请[" + event_code + "]");
			return "ok";
		} else {
			if (type.equals("dept")) {
				event_code = getCurUser().getUser_iidd() + "_PUNISHDEPT_" + System.currentTimeMillis();
			} else if (type.equals("seccheck")) {
				event_code = getCurUser().getUser_iidd() + "_PUNISHSECCHECK_" + System.currentTimeMillis();
			} else if (type.equals("rectify")) {
				event_code = getCurUser().getUser_iidd() + "_PUNISHRECTIFY_" + System.currentTimeMillis();
			}
			return SUCCESS;
		}
	}
}
