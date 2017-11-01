package hdsec.web.project.disc.action;

public class DelSpaceCDEventAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String del_type = "";

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setDel_type(String del_type) {
		this.del_type = del_type;
	}

	@Override
	public String executeFunction() throws Exception {
		discService.delSpaceCDEventByEventCode(event_code);
		insertCommonLog("删除空白盘作业[" + event_code + "]");

		return "ok";
	}
}
