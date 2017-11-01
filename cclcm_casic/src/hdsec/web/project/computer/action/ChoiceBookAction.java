package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChoiceBookAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private List<EntityBook> bookList = null;
	private String barcode = "";
	private String type = "";
	private String job_code = "";
	private String conf_code = "";
	private String oldconf_code = "";
	private String duty_user_id = "";
	private String duty_user_name = "";

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<EntityBook> getBookList() {
		return bookList;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getOldconf_code() {
		return oldconf_code;
	}

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type.equals("")) {
			/*
			 * if (!event_code.equals("")) { map.put("event_code", event_code); eventList =
			 * computerService.getBorrowBookEventList(map); if (eventList != null) { event = eventList.get(0); //
			 * map.put("seclv_code", event.getBook_selv());//是否固定筛选密级 } }
			 */
			map.put("duty_user_id", duty_user_id);
			map.put("conf_code", conf_code);
			map.put("oldconf_code", oldconf_code);
			map.put("book_status", "1");
			bookList = computerService.getBookList(map);
		} else {
			if (barcode.equals("")) {
				throw new Exception("该笔记本台账不存在，请重新选择");
			} else if (event_code.equals("")) {
				throw new Exception("该笔记本借用作业异常，请重新选择");
			} else {
				map.put("associat_entity", barcode);
				map.put("event_code", event_code);
				// 更新笔记本作业表中关联的台账条码号
				computerService.updateBorrowBookAssociate(map);
			}
		}
		return SUCCESS;
	}
}