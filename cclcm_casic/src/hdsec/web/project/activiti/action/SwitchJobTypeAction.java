package hdsec.web.project.activiti.action;

import hdsec.web.project.activiti.model.JobTypeEnum;

/**
 * 删除流程
 * @author renmingfei
 *
 */
public class SwitchJobTypeAction extends ActivitiBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String jobType_code = "";
	
	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (jobType_code.isEmpty()) {
			throw new Exception("参数错误，没有标记特征值");
		} else {
			JobTypeEnum item = JobTypeEnum.valueOf(jobType_code);
			item.switchUsed();
			insertAdminLog("修改流程类型启用状态：特征值[" + jobType_code + "]");
		}
		return SUCCESS;
	}
}
