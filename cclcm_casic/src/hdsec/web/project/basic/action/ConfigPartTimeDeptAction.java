package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.DeptAdminConfig;

/**
 * 为用户配置兼职审批部门201511
 * 
 * @author gaoxm
 */
public class ConfigPartTimeDeptAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String dept_ids = "";
	private DeptAdminConfig adminConfig = null;
	private String result = "";

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getDept_ids() {
		return dept_ids;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids.replaceAll(" ", "");
	}

	public DeptAdminConfig getAdminConfig() {
		return adminConfig;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String executeFunction() throws Exception {
		adminConfig = new DeptAdminConfig(user_iidd, "", dept_ids, "");
		basicService.configPartTimeDept(adminConfig);
		insertAdminLog("为" + userService.getUserNameByUserId(user_iidd) + "配置兼职审批部门");
		setResult("兼职审批部门配置成功");
		return SUCCESS;
	}
}
