package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.JobTypeEnum;

import java.util.List;

/**
 * 添加代理
 * 
 * @author yueying
 * 
 */
public class AddAprvProxyAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String executeFunction() throws Exception {
		return SUCCESS;
	}
	
	public String getUser_name() {
		return getCurUser().getUser_name();
	}
	
	public List<JobTypeEnum> getJobTypeList() {
		return JobTypeEnum.getUsedJobTypeList();
	}
	
}
