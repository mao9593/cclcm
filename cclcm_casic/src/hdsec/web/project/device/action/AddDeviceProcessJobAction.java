package hdsec.web.project.device.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.device.model.DeviceType;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

/**
 * 添加流程任务申请
 * 
 * @author lixiang
 * 
 */
public class AddDeviceProcessJobAction extends DeviceBaseAction {

	private static final long serialVersionUID = 1L;
	private Integer seclv_code = null;
	private String submit = "N";
	private String comment = "";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private final SecLevel seclv = null;
	private final ApproveProcess process = null;
	private final List<ApproverUser> userList = null;
	private String chkResult = "";
	private String usage_code = "";
	private String project_code = "";
	private String hiddens = "";

	public List<SecLevel> getSeclvList1() {
		// return userService.getDeviceSecLevelByUser(getCurUser().getUser_iidd());
		return userService.getSecLevel();
	}

	public List<SecLevel> getSeclvList() {
		return userService.getDeviceSecLevelByUser(getCurUser().getUser_iidd());
	}

	public void setHiddens(String hiddens) {
		this.hiddens = hiddens.replaceAll(" ", "");
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
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
		return basicService.getSysUsageListByModule("DEVICE");
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<DeviceType> getDeviceTypeList() {
		return deviceService.getDeviceTypeList();
	}

	@Override
	public String executeFunction() throws Exception {
		if (submit.equalsIgnoreCase("Y")) {
			try {
				deviceService.addProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
						jobType, next_approver, comment, hiddens, usage_code, project_code);
				insertCommonLog("提交" + jobType.getJobTypeName() + "申请");
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加审批流程出现异常");
			}
			setChkResult("ok");
			return "ok";
		} else {
			return SUCCESS;
		}
	}

}