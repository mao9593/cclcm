package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EventModify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 待审批密级变更任务列表
 * 
 * @author congxue 2015-4-26
 */
public class ManageModifyJobAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = ledgerService.getModifyCandidateListByUserId(getCurUser().getUser_iidd());
		applyList.addAll(candidateList);

		Map<String, Object> map = new HashMap<String, Object>();
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<EventModify> events = ledgerService.getModifyEventListByJobCode(job.getJob_code());
			for (EventModify event : events) {
				if (event.getEntity_type().equalsIgnoreCase("Paper")) {
					EntityPaper paper = ledgerService.getPaperByBarcode(event.getBarcode());
					if (paper != null) {
						event_names += paper.getFile_title() + "  ";
					}
					job.setSeclv_code(event.getPre_seclv());
					job.setPre_seclv(event.getPre_seclv());
					job.setTrg_seclv(event.getTrg_seclv());

					String seclv_name = basicService.getSeclvNameByCode(event.getPre_seclv());
					job.setPre_seclv_name(seclv_name);// 原密级

					seclv_name = basicService.getSeclvNameByCode(event.getTrg_seclv());
					job.setTrg_seclv_name(seclv_name);// 目标密级

				} else {
					EntityCD cd = ledgerService.getCDByBarcode(event.getBarcode());
					if (cd != null) {
						event_names += cd.getFile_list() + "";
					}
					job.setSeclv_code(event.getPre_seclv());
					job.setPre_seclv(event.getPre_seclv());
					job.setTrg_seclv(event.getTrg_seclv());

					String seclv_name = basicService.getSeclvNameByCode(event.getPre_seclv());
					job.setPre_seclv_name(seclv_name);// 原密级

					seclv_name = basicService.getSeclvNameByCode(event.getTrg_seclv());
					job.setTrg_seclv_name(seclv_name);// 目标密级
				}
			}
			job.setEvent_names(event_names);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "MODIFY_SECLV", 1);
		}
		return SUCCESS;
	}
}
