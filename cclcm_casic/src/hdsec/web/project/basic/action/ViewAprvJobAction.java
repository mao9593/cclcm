package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.borrow.model.EventBorrow;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看已审批任务列表
 * 
 * @author renmingfei
 * 
 */
public class ViewAprvJobAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;
	private String type = "";
	private String file_src;
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name.trim();
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		List<ProcessJob> tempList = null;
		jobList = new ArrayList<ProcessJob>();
		if (type.equalsIgnoreCase("BURN")) {// 刻录
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BURN_FILE,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BURN_REMAIN,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BURN_SEND,
					user_name, seclv_code);
			jobList.addAll(tempList);
			for (ProcessJob job : jobList) {
				String event_names = "";
				List<BurnEvent> events = burnService.getBurnEventListByJobCode(job.getJob_code());
				for (BurnEvent event : events) {
					event_names += event.getFile_list() + "  ";
				}
				job.setEvent_names(event_names);
			}
		} else if (type.equalsIgnoreCase("PRINT")) {
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_FILE,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_REMAIN,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_SEND,
					user_name, seclv_code);
			jobList.addAll(tempList);
		} else {
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.valueOf(type),
					user_name, seclv_code);
			jobList.addAll(tempList);

			if (type.equalsIgnoreCase("TRANSFER")) {// 流转
				for (ProcessJob job : jobList) {
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
				for (ProcessJob job : jobList) {
					EventBorrow event = borrowService.getBorrowEventByJobCode(job.getJob_code());
					if (event != null) {
						job.setEvent_names(event.getEntity_name());
					}
				}
			}
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
