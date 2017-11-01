package hdsec.web.project.activiti.action;

import hdsec.web.project.activiti.model.JobTypeEnum;

import java.util.List;

/**
 * 操作标记列表
 * @author renmingfei
 *
 */
public class ManageJobTypeAction extends ActivitiBaseAction {
	private static final long serialVersionUID = 1L;
	
	public List<JobTypeEnum> getTypeList() {
		return JobTypeEnum.getAllJobTypeList();
	}
	
	@Override
	public String executeFunction() throws Exception {
		return SUCCESS;
	}
}
