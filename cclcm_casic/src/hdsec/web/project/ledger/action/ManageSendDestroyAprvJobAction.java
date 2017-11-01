package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.SendDestroyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author lixiang
 * 
 */
public class ManageSendDestroyAprvJobAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SENDES_PAPER);
		applyList.addAll(candidateList);

		for (ProcessJob job : applyList) {
			String event_names = "";
			List<SendDestroyEvent> events = ledgerService.getSendDestroyEventListByJobCode(job.getJob_code());
			for (SendDestroyEvent event : events) {
				EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
				if (paper != null) {
					event_names += paper.getFile_title() + "  ";
				}
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SENDES", 1);
		return SUCCESS;
	}
}
