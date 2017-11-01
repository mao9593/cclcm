package hdsec.web.project.basic.action;

import hdsec.web.project.burn.action.BurnBaseAction;
import hdsec.web.project.user.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 模糊查询用户列表
 * 
 * @author renmingfei
 * 
 */
public class GetFuzzyUserAction extends BurnBaseAction {

	private static final long serialVersionUID = 1L;
	private String fuzzy = "";
	private List<User> userList = new ArrayList<User>();
	private String source = "";

	public void setSource(String source) {
		this.source = source;
	}

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
			userList = userService.getFuzzyUserDept("01", fuzzy);
		} catch (Exception e) {
			logger.error("Exception occurs in GetFuzzyUserAction");
			logger.error(e.getMessage());
		}
		return source.equals("CR") ? "CR" : SUCCESS;
	}
}
