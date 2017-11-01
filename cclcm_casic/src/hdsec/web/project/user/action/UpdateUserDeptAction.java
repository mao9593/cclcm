package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecRole;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.SecUserDept;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 修改用户部门
 * @author renmingfei
 *
 */
public class UpdateUserDeptAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	private String old_dept_id = "";
	private String user_iidd = "";
	private String prompt = "N";//用于新用户部门不包含用户岗位的时候返回页面提示
	private SecUserDept userDept = null;
	private List<SecRole> roleList = null;
	private String clearPost = "N";
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getOld_dept_id() {
		return old_dept_id;
	}
	
	public void setOld_dept_id(String old_dept_id) {
		this.old_dept_id = old_dept_id;
	}
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getPrompt() {
		return prompt;
	}
	
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
	public SecUserDept getUserDept() {
		return userDept;
	}
	
	public List<SecRole> getRoleList() {
		return roleList;
	}
	
	public void setClearPost(String clearPost) {
		this.clearPost = clearPost;
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_iidd", user_iidd);
		map.put("dept_id", dept_id);
		map.put("old_dept_id", old_dept_id);
		map.put("clearPost", clearPost);
		if (clearPost.equalsIgnoreCase("Y")) {//页面已经确认强制修改部门
			userService.updateUserDept(map);
			String dept_name = userService.getDeptNameByDeptId(dept_id);
			insertAdminLog("更换用户部门:" + user_iidd + "," + dept_name);
			return "done";
		} else {
			SecUser secUser = userService.getSecUserByUid(user_iidd);
			String post_id = secUser.getPost_id();
			if (StringUtils.hasLength(post_id)) {//如果用户本来拥有岗位
				Map<String, String> map1 = new HashMap<String, String>();
				if (post_id == null) {
					map1.put("post_id", "-1");
				} else {
					map1.put("post_id", post_id);
				}
				map1.put("dept_id", dept_id);
				if (userService.getDeptPostCount(map1) == 0) {//如果新部门没有用户原本的岗位，则返回页面询问是否修改
					setPrompt("Y");
					userDept = userService.getUserMainDeptByUser(user_iidd);
					String subsys_code = "eoms";
					map1 = new HashMap<String, String>();
					map1.put("user_iidd", user_iidd);
					//map1.put("role_type", String.valueOf(Constants.ROLE_TYPE_COMMON));
					map1.put("subsys_code", subsys_code);
					roleList = userService.getRoleListByUser(map1);
					return SUCCESS;
				} else {//如果新部门有用户本来的岗位，则直接修改部门
					userService.updateUserDept(map);
					String dept_name = userService.getDeptNameByDeptId(dept_id);
					insertAdminLog("更换用户部门:" + user_iidd + "," + dept_name);
					return "done";
				}
			} else {//如果用户本来的岗位就为空，则直接修改
				userService.updateUserDept(map);
				String dept_name = userService.getDeptNameByDeptId(dept_id);
				insertAdminLog("更换用户部门:" + user_iidd + "," + dept_name);
				return "done";
			}
		}
		//return SUCCESS;
	}
}
