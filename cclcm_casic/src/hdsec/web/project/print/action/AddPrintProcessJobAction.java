package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.print.model.FixAccording;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecRole;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
	private String _chk = "";
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
	private String proxyprint_user_name = "";
	private String proxyprint_user_iidd = "";
	private String file_scope = ""; // 文件知悉范围
	private String seclv_accord = ""; // 文件定密依据
	private String secret_time = null; // 文件保密期限

	private String is_special_print = "N"; // 是否为特殊打印流程标识
	private String is_merge = ""; // 是否合并复印
	private Integer copy_num = 0; // 合并复印张数
	

	public Integer getCopy_num() {
		return copy_num;
	}

	public void setCopy_num(Integer copy_num) {
		this.copy_num = copy_num;
	}

	public String getIs_merge() {
		return is_merge;
	}

	public void setIs_merge(String is_merge) {
		this.is_merge = is_merge;
	}

	public String getIs_special_print() {
		return is_special_print;
	}

	public void setIs_special_print(String is_special_print) {
		this.is_special_print = is_special_print;
	}

	public String getFile_scope() {
		return file_scope;
	}

	public void setFile_scope(String file_scope) {
		this.file_scope = file_scope;
	}

	public String getSeclv_accord() {
		return seclv_accord;
	}

	public void setSeclv_accord(String seclv_accord) {
		this.seclv_accord = seclv_accord;
	}

	public String getSecret_time() {
		return secret_time;
	}

	public void setSecret_time(String secret_time) {
		this.secret_time = secret_time;
	}

	public String get_chk() {
		return _chk;
	}

	public String getProxyprint_user_name() {
		return proxyprint_user_name;
	}

	public void setProxyprint_user_name(String proxyprint_user_name) {
		this.proxyprint_user_name = proxyprint_user_name;
	}

	public String getProxyprint_user_iidd() {
		return proxyprint_user_iidd;
	}

	public void setProxyprint_user_iidd(String proxyprint_user_iidd) {
		this.proxyprint_user_iidd = proxyprint_user_iidd;
	}

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

	public void set_chk(String _chk) {
		this._chk = _chk.replaceAll(" ", "");
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
	
	@Override
	public String executeFunction() throws Exception {
		if (submit.equalsIgnoreCase("Y")) {

			try {
				Date start_time = new Date();
				printService.addProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
						cycle_type, period, jobType, next_approver, output_dept_name, output_user_name, comment,
						StringUtil.stringArrayToList(_chk.split(",")), usage_code, project_code, getFileSeclvList(),
						getFilePrintCountList(), getFileColorList(), getFilePrintDoubleList(), getFileTitleList(),
						start_time, getCopy_num());
				// printService.modifyEventApplyTime(start_time);

				insertCommonLog("提交" + jobType.getJobTypeName() + "申请:" + log_file_name);
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加审批流程出现异常");
			}

			if (StringUtils.hasLength(_chk)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("submitable", false);
				map.put("event_ids", StringUtil.stringArrayToList(_chk.split(",")));
				eventList = printService.getPrintEventList(map);
				String job_code_temp = "";
				String job_status_temp = "";
				if (eventList != null) {
					for (PrintEvent event : eventList) {
						if (!event.getEvent_code().isEmpty()) {
							PrintEvent tempprintevent = printService.getPrintEventByPrintCode(event.getEvent_code());
							tempprintevent.setFile_scope(file_scope);
							tempprintevent.setSeclv_accord(seclv_accord);
							tempprintevent.setSecret_time(secret_time);
							printService.updatePrintEvent1(tempprintevent);

							Map<String, Object> map1 = new HashMap<String, Object>();
							map1.put("event_code", event.getEvent_code());
							map1.put("proxyprint_user_iidd", proxyprint_user_iidd);
							printService.updatePrintProxyUseridByEventCode(map1);
							job_code_temp = event.getJob_code();
							job_status_temp = event.getJob_status();
						}
					}

					if (job_status_temp.equals("true")) {

						// 审批通过后， 获取刻录委托人,通知委托人
						String message_proxy = "委托给您的" + jobType.getJobTypeName() + "作业已审批通过，请及时打印";
						String oper_type_proxy = "PROXY_PRINT";
						Integer approveResultValue = 2;
						if (proxyprint_user_iidd != null && !proxyprint_user_iidd.equals("")) {
							String proxypburn_user_name = userService.getUserNameByUserId(proxyprint_user_iidd);
							ClientMsg clientMsg_proxy = new ClientMsg(proxyprint_user_iidd, proxypburn_user_name,
									oper_type_proxy, approveResultValue, job_code_temp, message_proxy, new Date(), 0);
							basicService.addClientMsg(clientMsg_proxy);
						}
					}
				}
			}

			/*
			 * for(SecRole secrole : getCurUser().getUserRoleList()){ if(secrole.getRole_id().equalsIgnoreCase("666")){
			 * return "print"; } }
			 */
			List<SecRole> userRoleList = getCurUser().getUserRoleList();
			if (userRoleList != null && userRoleList.size() > 0) {
				for (SecRole role : userRoleList) {
					if (role.getRole_id().equals("666")) {
						setChkResult("nnd");
						return "nnd";
					}
				}
			}
			setChkResult("ok");
			return "ok";
		} else {
			if (StringUtils.hasLength(_chk)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("submitable", true);
				map.put("event_ids", StringUtil.stringArrayToList(_chk.split(",")));
				eventList = printService.getPrintEventList(map);
				if (eventList != null) {
					for (PrintEvent event : eventList) {
						if (!event.getKeyword_content().isEmpty()) {
							keywords += event.getKeyword_content();
						}
					}
				}
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("submitable", true);
				map.put("user_iidd", getCurUser().getUser_iidd());
				eventList = printService.getPrintEventList(map);
				PrintEvent event = null;
				if (eventList != null) {
					event = eventList.get(0);
					eventList = new ArrayList<PrintEvent>();
					eventList.add(event);
				}
				if (!event.getKeyword_content().isEmpty()) {
					keywords += event.getKeyword_content();
				}
				/*
				 * for (PrintEvent event : eventList) { if (!event.getKeyword_content().isEmpty()) { keywords +=
				 * event.getKeyword_content(); } }
				 */
			}
			return SUCCESS;
		}
	}
}