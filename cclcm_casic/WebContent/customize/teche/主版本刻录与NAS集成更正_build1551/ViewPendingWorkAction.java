package hdsec.web.project.client.action;

import hdsec.web.project.client.model.PendingWorkItem;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 客户端查看待办列表 2014-4-14 下午2:03:22
 * 
 * @author renmingfei
 */
public class ViewPendingWorkAction extends ClientBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer setIsRead = 0;
	private String needApprove = "N";
	private List<PendingWorkItem> approvedList = new ArrayList<PendingWorkItem>();
	private List<PendingWorkItem> waitApproveList = new ArrayList<PendingWorkItem>();

	public void setSetIsRead(Integer setIsRead) {
		this.setIsRead = setIsRead;
	}

	public String getNeedApprove() {
		return needApprove;
	}

	public List<PendingWorkItem> getApprovedList() {
		return approvedList;
	}

	public List<PendingWorkItem> getWaitApproveList() {
		return waitApproveList;
	}

	@Override
	public String executeFunction() throws Exception {
		if (setIsRead == 1) {
			basicService.updateClientMsg(getCurUser().getUser_iidd(), "", 1, new Date());
		}
		SecUser secUser = getSecUserFromSession();
		String permission = "burn/managenasburnevent.action";
		boolean nasFlag = secUser.hasPermission(permission);
		approvedList = clientService.getWorkerWorkList(getCurUser().getUser_iidd(), nasFlag);
		// if (getCurUser().is_leader()) {
		waitApproveList = clientService.getLeaderWorkList(getCurUser().getUser_iidd(), nasFlag);
		// }
		if (waitApproveList != null && waitApproveList.size() > 0) {
			needApprove = "Y";
		}
		return SUCCESS;
	}

	@Override
	protected SecUser getSecUserFromSession() {
		HttpServletRequest request1 = getRequest();
		HttpSession s = request1.getSession();
		return (SecUser) s.getAttribute(Constants.SESSION_USER_KEY);
	}
}
