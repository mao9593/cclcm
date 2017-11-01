package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.common.BaseEvent;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.transfer.service.TransferService;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

/**
 * 审批任务
 * 
 * @author renmingfei
 * 
 */
public class ApproveJobAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<BaseEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String jobType;
	private String file_src;
	@Resource
	protected TransferService transferService;
	private String proxyoutput_user_name = "";

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

	public List<BaseEvent> getEventList() {
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
			ProcessJob jobdetail = basicService.getProcessJobByCode(job_code);
			String next_approver_name = basicService.getApproverName(next_approver);
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
			// 审批通过添加流转提醒消息
			ProcessJob jobTransfer = basicService.getProcessJobByCode(job_code);
			if (String.valueOf(jobTransfer.getJobType()).contains("TRANSFER")
					&& jobTransfer.getJob_status().equals("true")) {
				List<EventTransfer> transfers = transferService.getTransferEventByJobCode(job_code);
				if (transfers.size() > 0) {
					ClientMsg clientMsg = new ClientMsg(transfers.get(0).getAccept_user_iidd(), transfers.get(0)
							.getAccept_user_name(), "TRANSFER_CONFIRM", 8, transfers.get(0).getJob_code(), "流转确认",
							new Date(), 0);
					basicService.addClientMsg(clientMsg);
				}
			}
			// 预览后才能审批
			// Map<String, Object> map = new HashMap<String, Object>();
			// map.put("job_code", job_code);
			// map.put("file_read_status", 0);
			// basicService.updateJobProcessFileRead(map);

			insertCommonLog("审批流程申请任务[" + job_code + "]");
			return "ok";
		} else {
			job = basicService.getProcessJobByCode(job_code);
			jobType = basicService.getJobTypeCodeByJobCode(job_code);
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			eventList = basicService.getEventListByJobCode(job_code);
			String usage_code = eventList.get(0).getUsage_code();

			// 获取打印代理人或刻录代理人
			if (jobType.contains("PRINT")) {
				String proxyprint_user_iidd = basicService.getPrintProxyUserIdByJobcode(job_code);
				if (proxyprint_user_iidd != null && !proxyprint_user_iidd.equals("")) {
					setProxyoutput_user_name(userService.getUserNameByUserId(proxyprint_user_iidd));
				}
			} else if (jobType.contains("BURN")) {
				String proxyburn_user_iidd = basicService.getBurnProxyUserIdByJobcode(job_code);
				if (proxyburn_user_iidd != null && !proxyburn_user_iidd.equals("")) {
					setProxyoutput_user_name(userService.getUserNameByUserId(proxyburn_user_iidd));
				}
			}

			// process = basicService.getApproveProcessByKey(job.getDept_id(), job.getSeclv_code(), job.getJobType()
			// .getJobTypeCode());
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			try {
				List<ApproverUser> oriList = basicService.getNextApprover(job_code, job.getDept_id(),
						job.getSeclv_code(), job.getJobType().getJobTypeCode(), usage_code);
				userList = removeDuplicateList(oriList);
				if (!basicService.isSelfApprove()) {// 不允许自审批
					if ((userList != null) && (userList.size() == 1)
							&& userList.get(0).getUser_iidd().equals(job.getUser_iidd())) {
						throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请联系管理员添加可审批用户！");
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
					List<SecLevel> seclvList = new ArrayList<SecLevel>();
					if (jobType.contains("PRINT")) {
						seclvList = userService.getPrintSecLevelByUser(user.getUser_iidd());
					} else if (jobType.contains("BURN")) {
						seclvList = userService.getBurnSecLevelByUser(user.getUser_iidd());
					} else if (jobType.contains("COPY")) {
						seclvList = userService.getCopySecLevelByUser(user.getUser_iidd());
					} else if (jobType.contains("LEADIN")) {
						seclvList = userService.getImportSecLevelByUser(user.getUser_iidd());
					} else if (jobType.contains("DEVICE")) {
						seclvList = userService.getDeviceSecLevelByUser(user.getUser_iidd());
					} else {
						seclvList = userService.getSecLevel();
					}
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

	public List<EventTransfer> getTransfers() {
		List<EventTransfer> transfers = transferService.getTransferEventByJobCode(job_code);
		for (EventTransfer event : transfers) {
			if ("paper".equalsIgnoreCase(event.getEntity_type())) {
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
				if (null != paper) {
					event.setFile_name(paper.getFile_title());
				}
			} else {
				EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
				if (null != cd) {
					event.setFile_name(cd.getFile_list());
				}
			}
		}
		return transfers;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getFile_src() {
		return file_src;
	}

	public void setFile_src(String file_src) {
		this.file_src = file_src;
	}
}
