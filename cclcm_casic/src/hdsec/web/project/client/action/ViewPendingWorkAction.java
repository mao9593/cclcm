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
	private List<String> allOper = null;
	private List<String> nonOper = null;
	private boolean nasFlag = false;
	private String transferMsg = "N";
	private List<PendingWorkItem> transfers = new ArrayList<PendingWorkItem>();

	public String getTransferMsg() {
		return transferMsg;
	}

	public void setTransferMsg(String transferMsg) {
		this.transferMsg = transferMsg;
	}

	public List<PendingWorkItem> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<PendingWorkItem> transfers) {
		this.transfers = transfers;
	}

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

	public List<String> getAllOper() {
		return allOper;
	}

	public void setAllOper(List<String> allOper) {
		this.allOper = allOper;
	}

	public List<String> getNonOper() {
		return nonOper;
	}

	public void setNonOper(List<String> nonOper) {
		this.nonOper = nonOper;
	}

	public boolean isNasFlag() {
		return nasFlag;
	}

	public void setNasFlag(boolean nasFlag) {
		this.nasFlag = nasFlag;
	}

	public boolean hasPermission(String permission) {
		// 如果用户的权限列表中包含该权限字符串，则返回true
		if (allOper != null && allOper.contains(permission)) {
			return true;
		} else if (allOper != null && allOper.contains("/" + permission)) {
			return true;
		} else {
			// 数据库操作表中没有此操作记录，默认返回true;
			return false;
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (setIsRead == 1) {
			basicService.updateClientMsg(getCurUser().getUser_iidd(), "", 1, new Date());
		}
		SecUser secUser = getSecUserFromSession();
		if (secUser != null) {
			// 把用户的所有操作列表写进user
			List<String> allOperUrl = userService.getAllOperByUserOnly(secUser.getUser_iidd());
			secUser.setAllOper(allOperUrl);
			setAllOper(allOperUrl);
			// 把用户没有的操作列表写进user
			List<String> nonOperUrl = userService.getNonOperByUserOnly(secUser.getUser_iidd());
			secUser.setNonOper(nonOperUrl);
			setNonOper(nonOperUrl);
			String permission = "burn/managenasburnevent.action";
			nasFlag = hasPermission(permission);
		}
		approvedList = clientService.getWorkerWorkList(getCurUser().getUser_iidd(), nasFlag);
		// if (getCurUser().is_leader()) {
		waitApproveList = clientService.getLeaderWorkList(getCurUser().getUser_iidd(), nasFlag);
		// }
		if (waitApproveList != null && waitApproveList.size() > 0) {
			needApprove = "Y";
		}
		transfers = clientService.getTransferClient(getCurUser().getUser_iidd());
		if (transfers != null && transfers.size() > 0) {
			transferMsg = "Y";
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
