package hdsec.web.project.enter.action;

import org.springframework.util.StringUtils;

public class DeleteEnterJobAction extends EnterBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_delids = "";
	private String del_type = "";

	public String getEvent_delids() {
		return event_delids;
	}

	public void setEvent_delids(String event_delids) {
		this.event_delids = event_delids;
	}

	public String getDel_type() {
		return del_type;
	}

	public void setDel_type(String del_type) {
		this.del_type = del_type;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_delids)) {
			String[] ids = event_delids.split(":");
			for (String id : ids) {
				logger.info("id:" + id);
				enterService.deleteEnterJob(id);
				insertCommonLog("删除录入作业[" + id + "]");
			}
			if (del_type.equals("1")) {// 文件
				return "deletefile";
			}
			if (del_type.equals("2")) {// 光盘
				return "deletedisk";
			}
			if (del_type.equals("3")) {// 部门文件
				return "deletedepfile";
			}
			if (del_type.equals("4")) {// 部门光盘
				return "deletedepdisk";
			}
		}
		return "deletefile";
	}

}
