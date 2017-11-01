package hdsec.web.project.securityuser.action;

import hdsec.web.project.securityuser.model.SysUserPost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 岗位管理列表
 * 
 * @author yangjl 2015-6-30
 * 
 */
public class ManageUserPostAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysUserPost> userpostList = new ArrayList<SysUserPost>();

	// private List<SysUserPost> userpostList = null;

	public List<SysUserPost> getUserpostList() {
		return userpostList;
	}

	public void setUserpostList(List<SysUserPost> userpostList) {
		this.userpostList = userpostList;
	}

	private String is_sealed = "N";

	public String getIs_sealed() {
		return is_sealed;
	}

	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}

	/*
	 * private String getDeptNames(String dept_id) { String names = "";
	 * 
	 * return names; }
	 */
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_sealed", is_sealed);
		List<SysUserPost> tempList = securityUserService.getSysUserPostList(map);
		SysUserPost test = new SysUserPost();
		String id = "";
		for (SysUserPost userpost : tempList) {
			// String name = getDeptNames(userpost.getDept_id());
			// userpost.setDept_name();
			String dept_names = test.getDept_name();
			if (userpost.getPost_id().equals(id)) {
				dept_names = dept_names + " , " + userpost.getDept_name();
				test.setDept_name(dept_names);
			} else if (id != "") {
				userpostList.add(test);
				test = userpost;
				id = userpost.getPost_id();
			} else {
				test = userpost;
				id = userpost.getPost_id();
			}
		}
		userpostList.add(test);
		return SUCCESS;
	}
}
