package hdsec.web.project.activiti.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程管理
 * @author renmingfei
 *
 */
public class ManageProcessAction extends ActivitiBaseAction {
	
	private static final long serialVersionUID = 1L;
	private List<ApproveProcess> prcList = null;
	private String process_name = "";
	private String dept_id = "";
	private String dept_name = "";
	private String seclv_code = "";
	private String jobType_code = "";
	
	public String getProcess_name() {
		return process_name;
	}
	
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	
	public String getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public String getJobType_code() {
		return jobType_code;
	}
	
	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code;
	}
	
	public List<ApproveProcess> getPrcList() {
		return prcList;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	public List<JobTypeEnum> getTypeList() {
		return JobTypeEnum.getUsedJobTypeList();
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("process_name", process_name);
		map.put("seclv_code", seclv_code);
		map.put("dept_id", dept_id);
		map.put("jobType_code", jobType_code);
		prcList = activitiService.getApproveProcessList(map);
		return SUCCESS;
	}
	
}
