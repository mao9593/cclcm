package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EventLog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预台帐日志查询
 * 
 * @author zp
 * 
 */
public class ViewUnLockEventLogAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EventLog> eventLogs;
	private Date startTime;
	private Date endTime;
	private String user_name = "";
	private String dept_name = "";
	private String console_name = "";

	public List<EventLog> getEventLogs() {
		return eventLogs;
	}

	public void setEventLogs(List<EventLog> eventLogs) {
		this.eventLogs = eventLogs;
	}

	public String getStartTime_str() {
		return sdf.format(startTime);
	}

	public String getEndTime_str() {
		return sdf.format(endTime);
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getConsole_name() {
		return console_name;
	}

	public void setConsole_name(String console_name) {
		this.console_name = console_name;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("user_name", user_name);
		map.put("dept_name", dept_name);
		map.put("console_name", console_name);
		eventLogs = ledgerService.getEventLogAll(map);
		return SUCCESS;
	}

}
