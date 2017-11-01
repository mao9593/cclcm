package hdsec.web.project.client.action;

import hdsec.web.project.client.model.ClientTask;
import hdsec.web.project.client.model.PendingWorkItem;
import hdsec.web.project.user.model.SecRoleUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientTaskAction extends ClientBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ClientTask> taskList = new ArrayList<ClientTask>();
	private List<ClientTask> taskNoteList = new ArrayList<ClientTask>();
	private String message_type = null;
	private List<SecRoleUser> SecRoleUserList = new ArrayList<SecRoleUser>();
	private List<PendingWorkItem> transfers = new ArrayList<PendingWorkItem>();

	public List<PendingWorkItem> getTransfers() {
		return transfers;
	}

	public void setTransfers(List<PendingWorkItem> transfers) {
		this.transfers = transfers;
	}

	@Override
	public String executeFunction() throws Exception {
		transfers = clientService.getTransferClient(getCurUser().getUser_iidd());
		Map<String, String> umap = new HashMap<String, String>();
		umap.put("user_iidd", getCurUser().getUser_iidd());
		umap.put("is_proxy", "is_proxy");
		SecRoleUserList = userService.getAgentByUser_iidd(umap);
		String agent = getCurUser().getUser_iidd();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accept_user_iidd", agent);
		map.put("message_type", 1);
		taskList = clientService.getTask(map);
		for (SecRoleUser item : SecRoleUserList) {
			map.put("accept_user_iidd", item.getAgent());
			map.put("message_type", 1);
			taskList.addAll(clientService.getTask(map));
		}

		Map<String, Object> noteMap = new HashMap<String, Object>();
		noteMap.put("accept_user_iidd", getCurUser().getUser_iidd());
		noteMap.put("message_note_type", "message_note_type");
		taskNoteList = clientService.getTask(noteMap);

		return SUCCESS;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public List<ClientTask> getTaskList() {
		return taskList;
	}

	public List<ClientTask> getTaskNoteList() {
		return taskNoteList;
	}

}
