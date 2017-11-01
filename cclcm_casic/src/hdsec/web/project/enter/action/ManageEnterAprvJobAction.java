package hdsec.web.project.enter.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.enter.model.EnterProcessJob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 待审批录入任务列表 2014-5-16 上午2:30:07
 * 
 * @author gaoximin
 */
public class ManageEnterAprvJobAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EnterProcessJob> candidateList = null;
	private List<EnterProcessJob> applyList = null;

	public List<EnterProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<EnterProcessJob>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proxy_user_iidd", getCurUser().getUser_iidd());
		map.put("proxy_type", "APPROVE");
		List<SysProxy> approvers=basicService.getApproveProxy(map);
		if (approvers!=null&&approvers.size()>0) { 
			for (SysProxy sysProxy : approvers) {
				if (!sysProxy.getIsExpire()) {
					candidateList = enterService.getLeadinCandidateListByUserId(sysProxy.getUser_iidd());
					applyList.addAll(candidateList);
				}
			}
		}
		candidateList = enterService.getLeadinCandidateListByUserId(getCurUser().getUser_iidd());
		applyList.addAll(candidateList);
		for (EnterProcessJob job : applyList) {
			String event_names = "";
			List<EnterEvent> events = enterService.getEnterEventListByJobCode(job.getJob_code());
			for (EnterEvent event : events) {
				event_names += event.getZipfile() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "LEADIN", 1);
		return SUCCESS;
	}

}
