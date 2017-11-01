package hdsec.web.project.activiti.model;

import java.io.Serializable;

/**
 * 流程定义类
 * 2014-4-22 下午11:42:21
 * 
 * @author renmingfei
 */
public class ApproveProcess implements Serializable {
	private static final long serialVersionUID = 1L;
	private String process_id = "";
	private String process_name = "";
	private String dept_id = "";
	private String seclv_code = "";
	private String jobType_code = "";
	private String jobType_name = "";
	private String step_dept = "";// 审批步骤-部门编号，以"#"分隔
	private String step_role = "";// 审批步骤-角色编号，以"#"分隔
	private Integer total_steps;// 审批步骤总数
	
	private String step_dept_name = "";
	private String step_role_name = "";
	private String dept_name = "";
	private String seclv_name = "";
	private String steps_dest = "";
	private String usage_code = "";
	private String usage_name = "";
	
	public String getProcess_id() {
		return process_id;
	}
	
	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}
	
	public String getProcess_name() {
		return process_name;
	}
	
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getSeclv_code_ext() {
		return "," + seclv_code + ",";
	}
	
	public String getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public String getJobType_code() {
		return jobType_code;
	}
	
	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code;
	}
	
	public String getJobType_code_ext() {
		return "," + jobType_code + ",";
	}
	
	public String getJobType_name() {
		return jobType_name;
	}
	
	public void setJobType_name(String jobType_name) {
		this.jobType_name = jobType_name;
	}
	
	public String getDept_name() {
		return dept_name;
	}
	
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	public String getSeclv_name() {
		return seclv_name;
	}
	
	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}
	
	public String getStep_dept() {
		return step_dept;
	}
	
	public void setStep_dept(String step_dept) {
		this.step_dept = step_dept;
	}
	
	public String getStep_role() {
		return step_role;
	}
	
	public void setStep_role(String step_role) {
		this.step_role = step_role;
	}
	
	public Integer getTotal_steps() {
		return total_steps;
	}
	
	public void setTotal_steps(Integer total_steps) {
		this.total_steps = total_steps;
	}
	
	public String getSteps_dest() {
		return steps_dest;
	}
	
	public void setSteps_dest(String steps_dest) {
		this.steps_dest = steps_dest;
	}
	
	public ApproveProcess() {
		super();
	}
	
	public String getStep_dept_name() {
		return step_dept_name;
	}
	
	public void setStep_dept_name(String step_dept_name) {
		this.step_dept_name = step_dept_name;
	}
	
	public String getStep_role_name() {
		return step_role_name;
	}
	
	public void setStep_role_name(String step_role_name) {
		this.step_role_name = step_role_name;
	}
	
	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getUsage_code_ext() {
		return "," + usage_code + ",";
	}
	
	public String getUsage_name() {
		return usage_name;
	}

	public void setUsage_name(String usage_name) {
		this.usage_name = usage_name;
	}

	public ApproveProcess(String process_id, String process_name, String dept_id, String seclv_code,
			String jobType_code, String step_dept, String step_role, Integer total_steps, String usage_code) {
		super();
		this.process_id = process_id;
		this.process_name = process_name;
		this.dept_id = dept_id;
		this.seclv_code = seclv_code;
		this.jobType_code = jobType_code;
		this.step_dept = step_dept;
		this.step_role = step_role;
		this.total_steps = total_steps;
		this.usage_code = usage_code;
	}
	
}
