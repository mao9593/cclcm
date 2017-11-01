package hdsec.web.project.log.action;

import hdsec.web.project.log.model.SystemLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看系统日志
 * @author renmingfei
 *
 */
public class ViewSystemLogAction extends LogBaseAction {
	
	private static final long serialVersionUID = 1L;
	private List<SystemLog> logList = null;
	private String subsys_name = "";
	private Integer log_type = null;
	
	public String getSubsys_name() {
		return subsys_name;
	}
	
	public Integer getLog_type() {
		return log_type;
	}
	
	public void setSubsys_name(String subsys_name) {
		this.subsys_name = subsys_name;
	}
	
	public void setLog_type(Integer log_type) {
		this.log_type = log_type;
	}
	
	public List<SystemLog> getLogList() {
		return logList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subsys_name", subsys_name);
		map.put("log_type", log_type);
		logList = logService.getSystemLog(map);
		return SUCCESS;
	}
}
