package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.borrow.model.EventBorrow;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 待审批任务列表
 * 
 * @author renmingfei
 * 
 */
public class ManageAprvJobAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	// private List<ProcessJob> assignedList = null;
	private List<ProcessJob> applyList = null;
	private String type = "";
	private String file_src;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proxy_user_iidd", getCurUser().getUser_iidd());
		map.put("proxy_type", "APPROVE");
		List<SysProxy> approvers = basicService.getApproveProxy(map);
		if (type.contains("BURN")) {// 刻录
			if (approvers != null && approvers.size() > 0) { // 刻录代理审批
				for (SysProxy sysProxy : approvers) {
					if (!sysProxy.getIsExpire()) {
						candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
								JobTypeEnum.BURN_FILE);
						applyList.addAll(candidateList);
						candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
								JobTypeEnum.BURN_REMAIN);
						applyList.addAll(candidateList);
						candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
								JobTypeEnum.BURN_SEND);
						applyList.addAll(candidateList);
						candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
								JobTypeEnum.SPECIAL_BURN_ZWYJG);
						applyList.addAll(candidateList);
					}
				}
			}
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BURN_FILE);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BURN_REMAIN);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BURN_SEND);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.SPECIAL_BURN_ZWYJG);
			applyList.addAll(candidateList);
			for (ProcessJob job : applyList) {
				String event_names = "";
				List<BurnEvent> events = burnService.getBurnEventListByJobCode(job.getJob_code());
				for (BurnEvent event : events) {
					event_names += event.getFile_list() + "  ";
				}
				job.setEvent_names(event_names);
			}
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), type, 1);
		} else if (type.equalsIgnoreCase("PRINT")) {
			if (approvers != null && approvers.size() > 0) { // 打印代理审批
				for (SysProxy sysProxy : approvers) {
					if (!sysProxy.getIsExpire()) {
						candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
								JobTypeEnum.PRINT_FILE);
						applyList.addAll(candidateList);
						candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
								JobTypeEnum.PRINT_REMAIN);
						applyList.addAll(candidateList);
						candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
								JobTypeEnum.PRINT_SEND);
						applyList.addAll(candidateList);
					}
				}
			}
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_FILE);
			applyList.addAll(candidateList);
			candidateList = basicService
					.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_REMAIN);
			applyList.addAll(candidateList);
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_SEND);
			applyList.addAll(candidateList);
		} else {
			if (approvers != null && approvers.size() > 0) { // 其他代理审批
				for (SysProxy sysProxy : approvers) {
					if (!sysProxy.getIsExpire()) {
						candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
								JobTypeEnum.valueOf(type));
						applyList.addAll(candidateList);
					}
				}
			}
			candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.valueOf(type));
			applyList.addAll(candidateList);

			if (type.equalsIgnoreCase("TRANSFER")) {// 流转
				for (ProcessJob job : applyList) {
					String event_names = "";
					List<EventTransfer> events = transferService.getTransferEventByJobCode(job.getJob_code());
					for (EventTransfer event : events) {
						if (event.getEntity_type().equalsIgnoreCase("Paper")) {
							EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
							if (paper != null) {
								event_names += paper.getFile_title() + "  ";
							}
						} else {
							EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
							if (cd != null) {
								event_names += cd.getFile_list() + "  ";
							}
						}
					}
					job.setEvent_names(event_names);
				}
			} else if (type.equalsIgnoreCase("BORROW")) {// 部门载体借用
				for (ProcessJob job : applyList) {
					EventBorrow event = borrowService.getBorrowEventByJobCode(job.getJob_code());
					if (event != null) {
						job.setEvent_names(event.getEntity_name());
					}
				}
			}

			basicService.setClientMsgRead(getCurUser().getUser_iidd(), type, 1);
		}
		return SUCCESS;
	}

	public String getFile_src() {
		return file_src;
	}

	public void setFile_src(String file_src) {
		this.file_src = file_src;
	}

}
