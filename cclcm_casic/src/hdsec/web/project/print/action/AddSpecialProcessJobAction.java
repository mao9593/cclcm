package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加特殊打印流程任务申请
 * 
 * @author gguojiao
 */
public class AddSpecialProcessJobAction extends PrintBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_ids = "";
	private Integer seclv_code = null;
	private String submit = "N";
	private String comment = "";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private SecLevel seclv = null;
	private ApproveProcess process = null;
	private List<ApproverUser> userList = null;
	private String chkResult = "";
	private String actionContext = "";
	private String project_code = "";
	private String usage_code = "";
	private List<OaPrintEvent> eventList = null;
	private Integer highest_seclv = 10000;
	private String add_type = "";

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public String getAdd_type() {
		return add_type;
	}

	public void setAdd_type(String add_type) {
		this.add_type = add_type;
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

	public void setEvent_ids(String event_ids) {
		this.event_ids = event_ids.replaceAll(" ", "");
	}

	public String getEvent_ids() {
		return event_ids;
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

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("PRINT");
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<OaPrintEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getImportSecLevelByUser(getCurUser().getUser_iidd());
	}

	public int getHighest_seclv() {
		return highest_seclv;
	}

	@Override
	public String executeFunction() throws Exception {
		if (submit.equalsIgnoreCase("Y")) {
			try {
				Date start_time = new Date();
				printService.addProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code, "", "",
						jobType, next_approver, "", "", comment, StringUtil.stringArrayToList(event_ids.split(":")),
						usage_code, project_code, null, null, null, null, null, start_time);

				insertCommonLog("提交" + jobType.getJobTypeName() + "审批申请" + event_ids);
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加审批流程出现异常");
			}
			return "ok";
		} else {
			if (StringUtils.hasLength(event_ids)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("submitable", true);
				map.put("event_ids", StringUtil.stringArrayToList(event_ids.split(":")));
				eventList = printService.getSpecialEventList(map);
				for (OaPrintEvent item : eventList) {
					SecLevel seclevel = userService.getSecLevelByCode(item.getFile_selv());
					if (highest_seclv > seclevel.getSeclv_rank()) {
						highest_seclv = seclevel.getSeclv_rank();
					}
				}
			} else {
				throw new Exception("参数错误，没有录入作业编号");
			}
		}
		seclv = userService.getSecLevelByCode(seclv_code);
		return SUCCESS;
	}
}
