package hdsec.web.project.borrow.action;

import hdsec.web.project.borrow.model.OpenScopeConfig;

/**
 * 配置部门涉密载体借用范围
 * 2014-4-16 下午10:35:14
 * 
 * @author renmingfei
 */
public class ConfigOpenScopeAction extends BorrowBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	private String dept_ids = "";
	private String config = "N";
	private OpenScopeConfig scopeConfig = null;
	
	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids.replaceAll(" ", "");
	}
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public void setConfig(String config) {
		this.config = config;
	}
	
	public String getConfig() {
		return config;
	}
	
	public OpenScopeConfig getScopeConfig() {
		return scopeConfig;
	}
	
	public String getDept_name() {
		return userService.getDeptNameByDeptId(dept_id);
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (config.equalsIgnoreCase("Y")) {
			scopeConfig = new OpenScopeConfig(dept_id, dept_ids);
			borrowService.configOpenScope(scopeConfig);
			insertAdminLog("配置部门涉密载体借用范围：" + userService.getDeptNameByDeptId(dept_id));
		}
		scopeConfig = borrowService.getOpenScopeConfig(dept_id);
		return SUCCESS;
	}
}
