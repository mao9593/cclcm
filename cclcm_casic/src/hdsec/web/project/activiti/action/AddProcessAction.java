package hdsec.web.project.activiti.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加流程
 * @author renmingfei
 *
 */
public class AddProcessAction extends ActivitiBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String process_name = "";
	private String dept_id = "";
	private String seclv_code = "";
	private String jobType_code = "";
	private String usage_code = "";
	private String step_dept = "";//审批步骤-部门编号，以"#"分隔
	private String step_role = "";//审批步骤-角色编号，以"#"分隔	
	
	public List<JobTypeEnum> getTypeList() {
		return JobTypeEnum.getUsedJobTypeList();
	}
	
	public void setProcess_name(String process_name) {
		this.process_name = process_name;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code.replace(" ", "");
	}
	
	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code.replace(" ", "");
	}
	
	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code.replace(" ", "");
	}

	public void setStep_dept(String step_dept) {
		this.step_dept = step_dept.replace(",", "#").replace(" ", "");
	}
	
	public void setStep_role(String step_role) {
		this.step_role = step_role.replace(",", "#").replace(" ", "");
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}
	@Override
	public String executeFunction() throws Exception {
		if (process_name.isEmpty()) {
			return SUCCESS;
		} else {
			Integer total_steps = 0;
			if (StringUtils.hasLength(step_dept)) {
				total_steps = step_dept.split("#").length;
				int total_roles = step_role.split("#").length;
				if (total_steps != total_roles) {
					throw new Exception("审批步骤中部门和角色数目不一致，请重新设置");
				}
			}
			String process_id = String.valueOf(System.currentTimeMillis());
			ApproveProcess process = new ApproveProcess(process_id, process_name, dept_id, seclv_code, jobType_code,
					step_dept, step_role, total_steps, usage_code);
			activitiService.addProcess(process);
			insertAdminLog("添加流程：" + process_name);
		}
		return "ok";
	}
}
