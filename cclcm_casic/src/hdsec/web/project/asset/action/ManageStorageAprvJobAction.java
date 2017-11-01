package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.asset.model.StorageEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 资产入库待审批任务
 * 
 * @author gaoximin 2015-7-29
 */
public class ManageStorageAprvJobAction extends AssetBaseAction {
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
				.getUser_iidd(), JobTypeEnum.STORE);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<StorageEvent> events = assetService
					.getStorageEventListByJobCode(job.getJob_code());
			for (StorageEvent event : events) {
				event_names += event.getProperty_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "STORE", 1);
		return SUCCESS;
	}

}
