package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.SysProxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 待审批闭环任务列表
 * 
 * @author renmingfei
 * 
 */
public class ManageHandleJobAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ProcessJob> jobList = new ArrayList<ProcessJob>();
	private List<ProcessJob> tempList = null;
	private String type = "";

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proxy_user_iidd", getCurUser().getUser_iidd());
		map.put("proxy_type", "APPROVE");
		List<SysProxy> approvers = basicService.getApproveProxy(map);
		if (approvers != null && approvers.size() > 0) { // 代理审批
			for (SysProxy sysProxy : approvers) {
				if (!sysProxy.getIsExpire()) {
					tempList = basicService.getHandleJobListByEntity(sysProxy.getUser_iidd(), type);
					jobList.addAll(tempList);
				}
			}
		}
		tempList = basicService.getHandleJobListByEntity(getCurUser().getUser_iidd(), type);
		jobList.addAll(tempList);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), type, 1);
		return SUCCESS;
	}

}
