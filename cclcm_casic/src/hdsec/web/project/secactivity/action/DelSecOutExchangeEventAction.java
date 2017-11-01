package hdsec.web.project.secactivity.action;

/**
 * 撤销涉外交流任务申请
 * 
 * @author gj
 */
public class DelSecOutExchangeEventAction extends SecuActiBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		secactivityservice.delSecOutExchangeEventByEventCode(event_code);
		insertCommonLog("删除涉外交流申请作业[" + event_code);
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
