package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.BaseEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加流程任务申请
 * 
 * @author renmingfei
 * 
 */
public class AddProcessJobAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_codes = "";
	private Integer seclv_code = null;
	private String submit = "N";
	private String output_dept_name = "";
	private String output_user_name = "";
	private String comment = "";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private SecLevel seclv = null;
	private final ApproveProcess process = null;
	private final List<ApproverUser> userList = null;
	private String chkResult = "";
	private String actionContext = "";
	private List<SecLevel> seclvList = null;
	private List<BaseEvent> eventList = null;
	private String one_cycle_type;
	private String usage_code = "";
	private String project_code = "";

	public List<SecLevel> getSeclvList() {
		return seclvList;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public void setOutput_dept_name(String output_dept_name) {
		this.output_dept_name = output_dept_name;
	}

	public void setOutput_user_name(String output_user_name) {
		this.output_user_name = output_user_name;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getActionContext() {
		return actionContext;
	}

	public void setActionContext(String actionContext) {
		this.actionContext = actionContext;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public void setEvent_codes(String event_codes) {
		this.event_codes = event_codes;
	}

	public String getEvent_codes() {
		return event_codes;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = JobTypeEnum.valueOf(jobType);
	}

	public SecLevel getSeclv() {
		return seclv;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	@Override
	public String executeFunction() throws Exception {
		if (submit.equalsIgnoreCase("Y")) {
			try {
				jobType = changeJobType();
				basicService.addProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code, jobType,
						next_approver, output_dept_name, output_user_name, comment, event_codes, usage_code,
						project_code);
				insertCommonLog("提交" + jobType.getJobTypeName() + "审批申请" + event_codes);
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加审批流程出现异常");
			}
			setChkResult("ok" + ":" + actionContext);
			return "ok";
		} else {
			if (StringUtils.hasLength(event_codes)) {
				eventList = basicService.getEventListByEventCodes(changeJobType(), event_codes);
				List<Integer> seclvCodeList = new ArrayList<Integer>();
				for (BaseEvent item : eventList) {
					seclvCodeList.add(item.getSeclv_code());
				}
				seclv = basicService.getHighestSeclvByCodeList(seclvCodeList);

				if (jobType == JobTypeEnum.DEVICE) {
					seclvList = new ArrayList<SecLevel>();
					seclvList.add(seclv);
				} else {
					seclvList = basicService.getHigherSeclvList(seclv);
				}
			} else {
				throw new Exception("参数错误，没有业务作业流水号");
			}
			return SUCCESS;
		}
	}

	private JobTypeEnum changeJobType() {
		if (null == one_cycle_type || "".equals(one_cycle_type) || null == jobType) {
			return jobType;
		}
		String job_type = jobType.getJobTypeCode();
		if (job_type.contains(one_cycle_type)) {
			return jobType;
		} else {
			String prexName = job_type.split("_")[0];
			String newJobTypeCode = prexName + "_" + one_cycle_type;
			for (JobTypeEnum job : JobTypeEnum.getAllJobType()) {
				if (job.getJobTypeCode().equals(newJobTypeCode)) {
					return job;
				}
			}
			return jobType;
		}
	}

	public String getOne_cycle_type() {
		return one_cycle_type;
	}

	public void setOne_cycle_type(String one_cycle_type) {
		this.one_cycle_type = one_cycle_type;
	}
}