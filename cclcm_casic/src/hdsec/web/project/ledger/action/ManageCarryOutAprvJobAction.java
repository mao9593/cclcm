package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EventCarryOut;

import java.util.ArrayList;
import java.util.List;

/**
 * 待审批外带任务列表
 * 
 * @author fyp
 * 
 */
public class ManageCarryOutAprvJobAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.CARRYOUT_PAPER);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.CARRYOUT_CD);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<EventCarryOut> events = ledgerService.getEventCarryOutListByJobCode(job.getJob_code());
			for (EventCarryOut event : events) {
				if (event.getEntity_Type().equals("Paper")) {
					EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
					event_names += paper.getFile_title() + "  ";
				} else {
					EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
					event_names += cd.getFile_list() + "  ";
				}

			}
			job.setEvent_names(event_names);
		}

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "CARRYOUT", 1);
		return SUCCESS;
	}

}
