package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 查看待关闭的错误流程列表
 * 
 * @author lixiang
 * 
 */
public class ViewCloseJobAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String user_name = "";
	private String seclv_code = "";
	private List<ProcessJob> jobList = null;
	
	public String getStartTime_str() {
		return startTime == null ? "" : sdf.format(startTime);
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime_str() {
		return endTime == null ? "" : sdf.format(endTime);
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name.trim();
	}

	public String getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	public List<ProcessJob> getJobList() {
		return jobList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("user_name", user_name);
		map.put("seclv_code", seclv_code);
		map.put("job_status", "under");//审批中
		map.put("missing_next_approver", true);//缺少下级审批人
		//map.put("jobType_code", JobTypeEnum.COPY.getJobTypeCode());
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		totalSize = basicService.getJobSize(map);
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		jobList = basicService.getJobList(map,rbs);
		return SUCCESS;
	}
}
