package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.print.model.RiskKeywordsPrint;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

/**
 * 审批任务
 * 
 * @author renmingfei
 * 
 */
public class ApprovePrintJobAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<PrintEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String proxyoutput_user_name = "";
	private String view_file_scope = "";
	private String view_seclv_accord = "";
	private String view_secret_time = "";
	private String viewflag = "N";
	private int eventSize = 0;
	private int eventSize2 = 0;
	private List<RiskKeywordsPrint> dlpList = null;

	public String getViewflag() {
		return viewflag;
	}

	public void setViewflag(String viewflag) {
		this.viewflag = viewflag;
	}

	public String getView_file_scope() {
		return view_file_scope;
	}

	public void setView_file_scope(String view_file_scope) {
		this.view_file_scope = view_file_scope;
	}

	public String getView_seclv_accord() {
		return view_seclv_accord;
	}

	public void setView_seclv_accord(String view_seclv_accord) {
		this.view_seclv_accord = view_seclv_accord;
	}

	public String getView_secret_time() {
		return view_secret_time;
	}

	public void setView_secret_time(String view_secret_time) {
		this.view_secret_time = view_secret_time;
	}

	public String getProxyoutput_user_name() {
		return proxyoutput_user_name;
	}

	public void setProxyoutput_user_name(String proxyoutput_user_name) {
		this.proxyoutput_user_name = proxyoutput_user_name;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public List<PrintEvent> getEventList() {
		return eventList;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion + " ";
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver.replaceAll(" ", "");
	}

	public String getType() {
		return basicService.getJobTypeCodeByJobCode(job_code);
	}

	public int getEventSize() {
		return eventSize;
	}

	public int getEventSize2() {
		return eventSize2;
	}

	/**
	 * 去重
	 * 
	 * @param oriList
	 * @return
	 */
	private List<ApproverUser> removeDuplicateList(List<ApproverUser> oriList) {
		Set<String> set = new HashSet<String>();
		List<ApproverUser> newList = new ArrayList<ApproverUser>();
		for (ApproverUser item : oriList) {
			if (set.add(item.getUser_iidd())) {
				newList.add(item);
			}
		}
		return newList;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(approved)) {// 审批结果
			// 多级审批时，必须每次审批均预览文件才能审批作业
			if (!next_approver.equals("")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("job_code", job_code);
				map.put("file_read_status", 0);
				basicService.updateJobProcessFileRead(map);

				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("job_code", job_code);
				printService.updateEventFileReadByJobCode(map1);
			}

			String next_approver_name = basicService.getApproverName(next_approver);
			ProcessJob jobdetail = basicService.getProcessJobByCode(job_code);
			ApproverUser user = null;
			if (jobdetail.getNext_approver().contains(getCurUser().getUser_iidd())) {
				user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("proxy_user_iidd", getCurUser().getUser_iidd());
				map.put("proxy_type", "APPROVE");
				List<SysProxy> approvers = basicService.getApproveProxy(map);
				for (SysProxy sysProxy : approvers) {
					if (jobdetail.getNext_approver().contains(sysProxy.getUser_iidd())) {
						user = new ApproverUser(sysProxy.getUser_iidd(), userService.getUserNameByUserId(sysProxy
								.getUser_iidd()));
						insertCommonLog(getCurUser().getUser_name() + "代理" + sysProxy.getUser_iidd() + "审批作业");
						break;
					}
				}
			}
			ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
			basicService.approveJob(job_code, user, approver, approved, opinion, "");
			String event_names = "";
			List<PrintEvent> events = printService.getPrintEventListByJobCode(job_code);
			for (PrintEvent event : events) {
				event_names += event.getFile_title() + ",";
			}
			insertCommonLog("审批打印任务，文件列表[" + event_names + "]");
			return "ok";
		} else {
			job = basicService.getProcessJobByCode(job_code);
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			eventList = printService.getPrintEventListByJobCode(job_code);

			// 开始获取每个文件对应的dlp检索结果，是否已经通过检测
			for (PrintEvent event : eventList) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("st_filename", event.getSt_filename());
				String transIDTemp = printService.getPrintTransID(map2);

				Map<String, Object> map3 = new HashMap<String, Object>();
				map3.put("tid", transIDTemp);
				dlpList = printService.getRisklistPrint(map3);

				String checkresult = printMapper.getCheckresultByTid(transIDTemp);

				if (null == dlpList || dlpList.size() == 0) {
					if (checkresult != null && !checkresult.isEmpty()) {
						if (checkresult.equals("NoHit")) {
							event.setPolicy("NO");
						} else if (checkresult.equals("Failed")) {
							event.setPolicy("Failed");
							eventSize2 = 1;
						} else if (checkresult.equals("RequestFailed")) {
							event.setPolicy("RequestFailed");
							eventSize2 = 1;
						} else if (checkresult.equals("Checking")) {
							event.setPolicy("Checking");
							eventSize2 = 1;
						}
					} else {
						event.setPolicy("Checking");
						eventSize2 = 1;
					}
				} else {
					event.setPolicy("YES");
					eventSize = eventSize + 1;
				}
			}

			String usage_code = eventList.get(0).getUsage_code();
			view_file_scope = eventList.get(0).getFile_scope();
			view_seclv_accord = eventList.get(0).getSeclv_accord();
			view_secret_time = eventList.get(0).getSecret_time();
			if (!view_file_scope.equals("") && !view_seclv_accord.equals("") && !view_secret_time.equals("")) {
				setViewflag("Y");
			}
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());

			// 获取打印代理人或刻录代理人
			String proxyprint_user_iidd = basicService.getPrintProxyUserIdByJobcode(job_code);
			if (proxyprint_user_iidd != null && !proxyprint_user_iidd.equals("")) {
				setProxyoutput_user_name(userService.getUserNameByUserId(proxyprint_user_iidd));
			}

			try {
				List<ApproverUser> oriList = basicService.getNextApprover(job_code, job.getDept_id(),
						job.getSeclv_code(), job.getJobType().getJobTypeCode(), usage_code);
				userList = removeDuplicateList(oriList);
				if (!basicService.isSelfApprove()) {// 不允许自审批
					if ((userList != null) && (userList.size() == 1)
							&& userList.get(0).getUser_iidd().equals(job.getUser_iidd())) {
						throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请先联系管理员添加可审批用户！");
					} else {
						for (ApproverUser user : userList) {
							if (user.getUser_iidd().equals(job.getUser_iidd())) {
								userList.remove(user);
								logger.debug("审批列表中去掉申请用户");
								break;
							}
						}
						for (ApproverUser user : userList) {
							if (user.getUser_iidd().equals(getCurUser().getUser_iidd())) {
								userList.remove(user);
								logger.debug("审批列表中去掉当前审批人");
								break;
							}
						}
					}
				}
				List<ApproverUser> tempList = new ArrayList<ApproverUser>();
				for (ApproverUser user : userList) {
					List<SecLevel> seclvList = userService.getPrintSecLevelByUser(user.getUser_iidd());
					for (SecLevel seclv : seclvList) {
						if (seclv.getSeclv_code() == job.getSeclv_code()) {
							tempList.add(user);
							break;
						}
					}
				}
				if (userList.size() > 0 && tempList.size() == 0) {
					throw new Exception("下级审批人涉密级别低于审批单密级，请联系管理员");
				}
				userList = tempList;
			} catch (Exception e) {
				logger.error("Exception:" + e.getMessage());
				if (e.getCause() instanceof TooManyResultsException) {
					logger.error("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
					throw new Exception("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
				} else {
					throw e;
				}
			}
			return SUCCESS;
		}
	}
}
