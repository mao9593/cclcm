package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ProcessJob;

/**
 * 关闭错误任务
 * @author lixiang
 *
 */
public class CloseWrongJobAction extends BasicBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	
	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (job_code.isEmpty()) {
			throw new Exception("参数错误，没有任务流水号");
		} else {
			ProcessJob job = basicService.getProcessJobByCode(job_code);
			String jobType_name = job.getJobType().getJobTypeName();
			String instance_id = job.getInstance_id();
			basicService.closeWrongJob(job_code);
			insertCommonLog("强制关闭"+jobType_name+"任务：任务号[" + job_code + "]流程实例ID["+ instance_id +"]");
		}
		return SUCCESS;
	}
}
