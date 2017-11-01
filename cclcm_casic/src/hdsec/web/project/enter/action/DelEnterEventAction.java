package hdsec.web.project.enter.action;

public class DelEnterEventAction extends EnterBaseAction {
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
		enterService.delEnterEventByEnterCode(event_code);
		insertCommonLog("删除录入作业[" + event_code + "]");
		if (del_type.equals("1")) {// 文件
			return "delfile";
		} else if (del_type.equals("2")) {// 光盘
			return "deldisk";
		} else if (del_type.equals("3")) {// 部门文件
			return "deldepfile";
		} else if (del_type.equals("4")) {// 部门光盘
			return "deldepdisk";
		} else if (del_type.equals("5")) {// 扫描录入
			return "delscanfile";
		}
		return "delfile";
	}
}
