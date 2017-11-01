package hdsec.web.project.log.action;

import hdsec.web.project.log.model.UserLoginLog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 查看用户登录日志
 * 
 * @author gaoximin
 * 
 */
public class ViewUserLoginLogAction extends LogBaseAction {
	
	private static final long serialVersionUID = 1L;
	private List<UserLoginLog> logList = null;
	private String user_name = "";
	private String dept_name = "";
	private String result = "";
	private String login_ip = "";
	private Date startTime = null;
	private Date endTime = null;
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name.trim();
	}
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name.trim();
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getLogin_ip() {
		return login_ip;
	}
	
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}
	
	public List<UserLoginLog> getLogList() {
		return logList;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public String getStartTime_str() {
		return sdf.format(startTime);
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getEndTime_str() {
		return sdf.format(endTime);
	}
	
	@Override
	public String executeFunction() throws Exception {
		String pageIndexName = new ParamEncoder("commonlog").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name", user_name);
		map.put("dept_name", dept_name);
		map.put("result", result);
		map.put("login_ip", login_ip);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = logService.getUserLoginLogSize(map);
		logList = logService.getUserLoginLog(map, rbs);
		return SUCCESS;
	}
}
