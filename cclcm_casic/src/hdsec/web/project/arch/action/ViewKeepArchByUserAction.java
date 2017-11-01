package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchTypeName;
import hdsec.web.project.arch.model.EventArchBrw;
import hdsec.web.project.arch.model.EventStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 普通用户查看当前自己当前借阅的档案
 * 
 * @author handouwang
 * 
 *         2015-7-29/
 */
public class ViewKeepArchByUserAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EventArchBrw> eventList = null;
	private String file_title = "";
	private String template_id = "";

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
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("borrow_status", EventStatus.LENT);
		eventList = archService.getArchBrwEventList(map);
		return SUCCESS;
	}
}
