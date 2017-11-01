package hdsec.web.project.change.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.change.model.EventChange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 待审批载体归属转换任务列表
 * 
 * @author lixiang
 * 
 */
public class ManageChangeAprvJobAction extends ChangeBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proxy_user_iidd", getCurUser().getUser_iidd());
		map.put("proxy_type", "APPROVE");
		List<SysProxy> approvers=basicService.getApproveProxy(map);
		if (approvers!=null&&approvers.size()>0) {
			for (SysProxy sysProxy : approvers) {
				if (!sysProxy.getIsExpire()) {
					candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(), JobTypeEnum.CHANGE);
					applyList.addAll(candidateList);
				}
			}
		}
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.CHANGE);
		applyList.addAll(candidateList);

		for (ProcessJob job : applyList) {
			String event_names = "";
			List<EventChange> events = changeService.getChangeEventListByJobCode(job.getJob_code());
			for (EventChange event : events) {
				event_names += event.getFile_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "CHANGE", 1);
		return SUCCESS;
	}

}
