package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.asset.model.PurchaseEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产采购待审批任务
 * 
 * @author gaoximin 2015-6-27
 */
public class ManagePurchaseAprvJobAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser()
				.getUser_iidd(), JobTypeEnum.PURCHASE);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser()
				.getUser_iidd(), JobTypeEnum.PURCHASES);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser()
				.getUser_iidd(), JobTypeEnum.URGENTPURCHASE);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<PurchaseEvent> events = assetService
					.getPurchaseEventListByJobCode(job.getJob_code());
			for (PurchaseEvent event : events) {
				event_names += event.getProperty_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PURCHASE",
				1);
		return SUCCESS;
	}

}
