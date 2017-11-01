package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.ProcessJob;

import java.util.List;

/**
 * 资产入库待审批，台账
 * 
 * @author gaoximin 2015-7-18
 */
public class ManageInAprvJobAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> jobList = null;

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	@Override
	public String executeFunction() throws Exception {
		jobList = assetService.getHandleInJobListByEntity(getCurUser()
				.getUser_iidd(), "PROPERTY");
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "STORE", 1);
		return SUCCESS;
	}

}
