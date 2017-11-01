package hdsec.web.project.secplace.action;

public class DeleteEventAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String type = "";

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		if (type.equalsIgnoreCase("ENTER")) {// 进入涉密场所申请撤销
			secplaceService.deleteEnterSecplaceEvent(event_code);
			return "ok_enter";
		} else {// 涉密场所申请撤销
			secplaceService.deleteSecplaceEvent(event_code);
			return SUCCESS;
		}
	}
}