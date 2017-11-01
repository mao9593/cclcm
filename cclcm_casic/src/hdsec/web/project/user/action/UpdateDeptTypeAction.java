package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecDeptType;

/**
 * 修改机构类型信息
 * @author renmingfei
 *
 */
public class UpdateDeptTypeAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private SecDeptType secDeptType = null;
	private String dept_type_code = null;
	private String dept_type_name = "";
	private String dept_type_desc = "";
	private String update = "N";
	
	public String getDept_type_code() {
		return dept_type_code;
	}
	
	public void setDept_type_code(String dept_type_code) {
		this.dept_type_code = dept_type_code;
	}
	
	public SecDeptType getSecDeptType() {
		return secDeptType;
	}
	
	public void setDept_type_name(String dept_type_name) {
		this.dept_type_name = dept_type_name;
	}
	
	public void setDept_type_desc(String dept_type_desc) {
		this.dept_type_desc = dept_type_desc;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {//处理修改操作
			secDeptType = new SecDeptType();
			secDeptType.setDept_type_code(dept_type_code);
			secDeptType.setDept_type_name(dept_type_name);
			secDeptType.setDept_type_desc(dept_type_desc);
			userService.updateDeptType(secDeptType);
			insertAdminLog("修改机构类型:" + dept_type_code + "," + dept_type_name);
			return "update";
		} else {
			secDeptType = userService.getDeptTypeByCode(dept_type_code);
		}
		return SUCCESS;
	}
}
