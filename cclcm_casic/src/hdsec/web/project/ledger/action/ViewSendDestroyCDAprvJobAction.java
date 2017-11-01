package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.SendDestroyEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看已审批送销任务列表
 * 
 * @author lixiang
 * 
 */
public class ViewSendDestroyCDAprvJobAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;

	// private String file_src;

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

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		List<ProcessJob> tempList = null;
		jobList = new ArrayList<ProcessJob>();
		tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SENDES_CD, user_name,
				seclv_code);
		jobList.addAll(tempList);

/*		for (ProcessJob job : jobList) {
			String event_names = "";
			List<SendDestroyEvent> events = ledgerService.getSendDestroyEventListByJobCode(job.getJob_code());
			for (SendDestroyEvent event : events) {
				EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
				if (cd != null) {
					event_names += cd.getFile_list() + "  ";
				}
			}
			job.setEvent_names(event_names);
		}*/
		return SUCCESS;
	}
}
