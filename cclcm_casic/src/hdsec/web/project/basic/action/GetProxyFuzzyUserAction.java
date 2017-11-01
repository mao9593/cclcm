package hdsec.web.project.basic.action;

import hdsec.web.project.burn.action.BurnBaseAction;
import hdsec.web.project.user.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 模糊查询用户列表
 * 
 * @author yy
 * 
 */
public class GetProxyFuzzyUserAction extends BurnBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String fuzzy = "";
	private List<User> userList = new ArrayList<User>();
	private String dept_id;
	
	public List<User> getUserList() {
		return userList;
	}
	
	public void setFuzzy(String fuzzy) {
		logger.warn("fuzzy is :" + fuzzy);
		this.fuzzy = fuzzy;
	}
	
	@Override
	public String executeFunction() throws Exception {
		try {
			dept_id = getCurUser().getDept_id();
			String parent_user_dept = basicService.getParentDeptIdByCurrentId(dept_id);
			userList = userService.getFuzzyUserDept(parent_user_dept, fuzzy);
		} catch (Exception e) {
			logger.error("Exception occurs in GetFuzzyUserAction");
			logger.error(e.getMessage());
		}
		return SUCCESS;
	}
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
}
