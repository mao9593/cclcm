package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 查看已审批任务列表
 * @author renmingfei
 *
 */
public class ViewHandleJobAction extends BasicBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;
	private String jobType_code = null;
	private String type = "";
	
	public String getUser_name() {
		return user_name;
	}
	
	public void setUser_name(String user_name) {
		this.user_name = user_name.trim();
	}
	
	public Integer getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public List<ProcessJob> getJobList() {
		return jobList;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getJobType_code() {
		return jobType_code;
	}
	
	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	@Override
	public String executeFunction() throws Exception {
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		
		totalSize = basicService.getHandledJobByUserIdSize(getCurUser().getUser_iidd(), type, user_name, seclv_code,
				jobType_code);
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		int beginIndex = rbs.getOffset();
		if (beginIndex > totalSize) {
			rbs = new RowBounds(0, 15);

		}
		
		jobList = basicService.getHandledJobByUserId(getCurUser().getUser_iidd(), type, user_name, seclv_code,
				jobType_code, rbs);
		if(type.equalsIgnoreCase("DEVICE")){
			return "device";
		}else{
			return SUCCESS;
		}
	}
	
}
