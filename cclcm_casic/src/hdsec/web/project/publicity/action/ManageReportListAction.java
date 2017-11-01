package hdsec.web.project.publicity.action;

import hdsec.web.project.publicity.model.ReportEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看用户宣传报道申请记录
 * 
 * @author LS
 */
public class ManageReportListAction extends PublicityBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_status = "";
	private String seclv_code = "";
	private String report_name = "";
	private List<ReportEvent> eventList = null;

	public String getReport_name() {
		return report_name;
	}

	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<ReportEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getActionContext() {
		return "/publicity/managereportlist.action";
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		map.put("report_name", report_name);
		eventList = publicityService.getPublReportEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT2", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT2", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT3", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT3", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_DEPTREPORT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_DEPTREPORT", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTRAPUBL", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTRAPUBL", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTERPUBL", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTERPUBL", 3);
		return SUCCESS;
	}
}
