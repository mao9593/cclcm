package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecDeptType;

import org.springframework.util.StringUtils;

/**
 * 添加机构类型
 * @author renmingfei
 *
 */
public class AddDeptTypeAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String dept_type_name;
	private String dept_type_desc;
	
	public void setDept_type_name(String dept_type_name) {
		this.dept_type_name = dept_type_name;
	}
	
	public void setDept_type_desc(String dept_type_desc) {
		this.dept_type_desc = dept_type_desc;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(dept_type_name)) {
			SecDeptType type = new SecDeptType();
			type.setDept_type_code(String.valueOf(System.currentTimeMillis()));
			type.setDept_type_name(dept_type_name);
			type.setDept_type_desc(dept_type_desc);
			userService.addDeptType(type);
			insertAdminLog("添加机构类型:" + dept_type_name);
			return "insert";
		}
		return SUCCESS;
	}
}
