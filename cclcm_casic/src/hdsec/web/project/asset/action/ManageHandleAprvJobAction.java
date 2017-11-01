package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.asset.model.WasteEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产变动待审批，台账
 * 
 * @author gaoximin 2015-7-29
 */
public class ManageHandleAprvJobAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	// private List<ProcessJob> jobList = null;
	//
	// public List<ProcessJob> getJobList() {
	// return jobList;
	// }

	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		// jobList =
		// assetService.getHandleJobListByEntity(getCurUser().getUser_iidd(),
		// "PROPERTY");
		// basicService.setClientMsgRead(getCurUser().getUser_iidd(),
		// "PROPERTYOUT", 1);
		// basicService.setClientMsgRead(getCurUser().getUser_iidd(), "WASTE",
		// 1);
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser()
				.getUser_iidd(), JobTypeEnum.WASTE);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser()
				.getUser_iidd(), JobTypeEnum.PROPERTYCHANGE);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<WasteEvent> events = assetService
					.getWasteEventListByJobCode(job.getJob_code());
			for (WasteEvent event : events) {
				event_names += event.getProperty_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "WASTE", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(),
				"PROPERTYCHANGE", 1);
		return SUCCESS;
	}

}
