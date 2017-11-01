package hdsec.web.project.enter.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加录入流程任务申请 2014-5-13 上午8:09:40
 * 
 * @author gaoximin
 */
public class AddEnterProcessJobAction extends EnterBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_ids = "";
	private Integer seclv_code = null;
	private String submit = "N";
	private String output_dept_name = "";
	private String output_user_name = "";
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
	private List<EnterEvent> eventList = null;
	private Integer highest_seclv = 10000;
	private String add_type = "";

	// private Integer temp = 100;

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public String getAdd_type() {
		return add_type;
	}

	public void setAdd_type(String add_type) {
		this.add_type = add_type;
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
		return basicService.getSysUsageListByModule("LEADIN");
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<EnterEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getImportSecLevelByUser(getCurUser().getUser_iidd());
	}

	@Override
	public String executeFunction() throws Exception {
		if (submit.equalsIgnoreCase("Y")) {
			try {
				if (jobType.toString().equals("LEADIN")) {
					enterService.addProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
							jobType, next_approver, output_dept_name, output_user_name, comment,
							StringUtil.stringArrayToList(event_ids.split(":")), usage_code, project_code);
				} else if (jobType.toString().equals("SCAN_LEADIN")) {
					enterService.addScanProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
							next_approver, output_dept_name, output_user_name, comment,
							StringUtil.stringArrayToList(event_ids.split(":")), usage_code, project_code);
				}
				insertCommonLog("提交" + jobType.getJobTypeName() + "审批申请" + event_ids);
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加审批流程出现异常");
			}
			if (add_type.equals("1")) {
				setChkResult("addfile");
			}
			if (add_type.equals("2")) {
				setChkResult("adddisk");
			}
			if (add_type.equals("3")) {
				setChkResult("adddepfile");
			}
			if (add_type.equals("4")) {
				setChkResult("adddepdisk");
			}
			if (add_type.equals("5")) {
				setChkResult("addscan");
			}
			if (add_type.equals("6")) {
				setChkResult("adddevice");
			}
			return "ok";
		} else {
			if (StringUtils.hasLength(event_ids)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("submitable", true);
				map.put("event_ids", StringUtil.stringArrayToList(event_ids.split(":")));
				eventList = enterService.getEnterEventList(map);
				for (EnterEvent item : eventList) {
					SecLevel seclevel = userService.getSecLevelByCode(item.getSeclv_code());
					if (highest_seclv > seclevel.getSeclv_rank()) {
						highest_seclv = seclevel.getSeclv_rank();
					}
					// 如果介质编号为空，赋空值，避免jsp页面单元格无边框
					if (item.getMedium_serial() == null || item.getMedium_serial().equals("")) {
						item.setMedium_serial("暂无");
					}
				}

			} else {
				throw new Exception("参数错误，没有录入作业编号");
			}
		}
		seclv = userService.getSecLevelByCode(seclv_code);
		return SUCCESS;
	}

	public int getHighest_seclv() {
		return highest_seclv;
	}

}
