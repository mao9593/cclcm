package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchTypeName;
import hdsec.web.project.arch.model.EventArchBrw;
import hdsec.web.project.arch.model.EventStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RenewArchAction extends ArchBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<EventArchBrw> eventList = null;
	private String file_title = "";
	private String template_id = "";
	private String user_iidd = "";
	private String user_name = "";
	private String job_status = "";

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public List<EventArchBrw> getEventList() {
		return eventList;
	}

	public List<ArchTypeName> getArchTypeList() {
		return archService.getValidTypeList();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file_title", file_title);
		map.put("template_id", template_id);
		map.put("user_iidd", user_iidd);
		map.put("user_name", user_name);
		map.put("borrow_status", EventStatus.UNRENEW + "");
		map.put("job_status", job_status);
		// map.put("job_status", ActivitiCons.APPLY_APPROVED_PASS);
		eventList = archService.getArchBrwEventList(map);
		return SUCCESS;
	}
}
