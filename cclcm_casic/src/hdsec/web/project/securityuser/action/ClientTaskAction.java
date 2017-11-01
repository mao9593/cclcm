package hdsec.web.project.securityuser.action;

import hdsec.web.project.common.bm.model.ClientTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientTaskAction extends SecurityUserBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ClientTask> taskList = new ArrayList<ClientTask>();
	private List<ClientTask> taskNoteList = new ArrayList<ClientTask>();

	// private String message_type = null;

	public List<ClientTask> getTaskList() {
		return taskList;
	}

	public List<ClientTask> getTaskNoteList() {
		return taskNoteList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accept_user_iidd", getCurUser().getUser_iidd());
		map.put("message_type", 1);
		taskList = securityUserService.getTask(map);

		Map<String, Object> noteMap = new HashMap<String, Object>();
		noteMap.put("accept_user_iidd", getCurUser().getUser_iidd());
		noteMap.put("message_note_type", "message_note_type");
		taskNoteList = securityUserService.getTask(noteMap);
		return SUCCESS;
	}

}
