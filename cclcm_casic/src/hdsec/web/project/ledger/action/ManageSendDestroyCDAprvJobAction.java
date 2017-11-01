package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.SendDestroyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 送销待审批任务列表
 * 
 * @author lixiang
 * 
 */
public class ManageSendDestroyCDAprvJobAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SENDES_CD);
		applyList.addAll(candidateList);

/*		for (ProcessJob job : applyList) {
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
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SENDES_CD", 1);
		return SUCCESS;
	}
}
