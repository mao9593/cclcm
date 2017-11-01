package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecRole;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加流程任务申请
 * 
 * @author renmingfei
 * 
 */
public class AddPrintProcessJobAction extends PrintBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_ids = "";
	private String cycle_type = "";
	private String period = "";
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
	private List<PrintEvent> eventList = null;
	private String usage_code = "";
	private String project_code = "";
	private String file_seclvs = "";
	private String file_printcounts = "";
	private String file_colors = "";
	private String file_printdoubles = "";
	private String file_titles = "";
	private String keywords = "";
	private String log_file_name = "";
	private List<SecRole> userRoleList = null;
	private String is_special = "N";

	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<PrintEvent> getEventList() {
		return eventList;
	}

	public void setFile_seclvs(String file_seclvs) {
		this.file_seclvs = file_seclvs;
	}

	public void setFile_printcounts(String file_printcounts) {
		this.file_printcounts = file_printcounts;
	}

	public void setFile_colors(String file_colors) {
		this.file_colors = file_colors;
	}

	public void setFile_printdoubles(String file_printdoubles) {
		this.file_printdoubles = file_printdoubles;
	}

	public void setFile_titles(String file_titles) {
		this.file_titles = file_titles;
	}

	public void setEvent_ids(String event_ids) {
		this.event_ids = event_ids.replaceAll(" ", "");
	}

	public String getEvent_ids() {
		return event_ids;
	}

	public void setCycle_type(String cycle_type) {
		this.cycle_type = cycle_type;
	}

	public void setPeriod(String period) {
		this.period = period;
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public boolean getIsKeywordEnable() {
		return basicService.isKeywordEnable();
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("PRINT");
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	private Map<String, String> getFileSeclvList() {
		if (StringUtils.hasLength(file_seclvs)) {
			Map<String, String> map = new HashMap<String, String>();
			for (String item : file_seclvs.split(",")) {
				if (item.indexOf(":") != -1) {
					map.put(item.substring(0, item.indexOf(":")), item.substring(item.indexOf(":") + 1));
				}
			}
			return map;
		}
		return Collections.emptyMap();
	}

	private Map<String, String> getFilePrintCountList() {
		if (StringUtils.hasLength(file_printcounts)) {
			Map<String, String> map = new HashMap<String, String>();
			for (String item : file_printcounts.split(",")) {
				if (item.indexOf(":") != -1) {
					map.put(item.substring(0, item.indexOf(":")), item.substring(item.indexOf(":") + 1));
				}
			}
			return map;
		}
		return Collections.emptyMap();
	}

	private Map<String, String> getFileColorList() {
		if (StringUtils.hasLength(file_colors)) {
			Map<String, String> map = new HashMap<String, String>();
			for (String item : file_colors.split(",")) {
				if (item.indexOf(":") != -1) {
					map.put(item.substring(0, item.indexOf(":")), item.substring(item.indexOf(":") + 1));
				}
			}
			return map;
		}
		return Collections.emptyMap();
	}

	private Map<String, String> getFilePrintDoubleList() {
		if (StringUtils.hasLength(file_printdoubles)) {
			Map<String, String> map = new HashMap<String, String>();
			for (String item : file_printdoubles.split(",")) {
				if (item.indexOf(":") != -1) {
					map.put(item.substring(0, item.indexOf(":")), item.substring(item.indexOf(":") + 1));
				}
			}
			return map;
		}
		return Collections.emptyMap();
	}

	private Map<String, String> getFileTitleList() {
		if (StringUtils.hasLength(file_titles)) {
			Map<String, String> map = new HashMap<String, String>();
			for (String item : file_titles.split(",")) {
				if (item.indexOf(":") != -1) {
					map.put(item.substring(0, item.indexOf(":")), item.substring(item.indexOf(":") + 1));
					log_file_name += item.substring(item.indexOf(":") + 1) + ",";
				}
			}
			return map;
		}
		return Collections.emptyMap();
	}

	public List<SecRole> getUserRoleList() {
		return userRoleList;
	}

	public String getIs_special() {
		return is_special;
	}

	@Override
	public String executeFunction() throws Exception {
		if (submit.equalsIgnoreCase("Y")) {
			try {
				printService.addProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
						cycle_type, period, jobType, next_approver, output_dept_name, output_user_name, comment,
						StringUtil.stringArrayToList(event_ids.split(":")), usage_code, project_code,
						getFileSeclvList(), getFilePrintCountList(), getFileColorList(), getFilePrintDoubleList(),
						getFileTitleList());
				insertCommonLog("提交" + jobType.getJobTypeName() + "申请:" + log_file_name);
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加审批流程出现异常");
			}
			setChkResult("ok");
			return "ok";
		} else {
			if (StringUtils.hasLength(event_ids)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("submitable", true);
				map.put("event_ids", StringUtil.stringArrayToList(event_ids.split(":")));
				eventList = printService.getPrintEventList(map);
				for (PrintEvent event : eventList) {
					if (!event.getKeyword_content().isEmpty()) {
						keywords += event.getKeyword_content();
					}
				}
				// 判断是否是打印专员（31所专用）
				userRoleList = getCurUser().getUserRoleList();
				if (userRoleList != null && userRoleList.size() > 0) {
					for (SecRole role : userRoleList) {
						if (role.getRole_spec_key().equalsIgnoreCase("SPECIALPAPERMANAGER")) {
							is_special = "Y";
						}
					}
				}
			} else {
				throw new Exception("参数错误，没有业务作业ID");
			}
			return SUCCESS;
		}
	}

}