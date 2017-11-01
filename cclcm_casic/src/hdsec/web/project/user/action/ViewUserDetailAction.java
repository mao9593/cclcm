package hdsec.web.project.user.action;

import hdsec.web.project.user.model.RealUser;
import hdsec.web.project.user.model.SecRole;
import hdsec.web.project.user.model.SecSubsys;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.SecUserPost;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewUserDetailAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private SecUser secUser = null;
	private SecUserPost secUserPost = null;
	private RealUser realUser = null;
	private List<SecSubsys> secSubsyslist = null;
	private List<Map<String, Object>> secRoleMap = null;
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public SecUser getSecUser() {
		return secUser;
	}
	
	public SecUserPost getSecUserPost() {
		return secUserPost;
	}
	
	public List<SecSubsys> getSecSubsyslist() {
		return secSubsyslist;
	}
	
	public List<Map<String, Object>> getSecRoleMap() {
		return secRoleMap;
	}
	
	public RealUser getRealUser() {
		return realUser;
	}
	
	@Override
	public String executeFunction() throws Exception {
		secUser = userService.getSecUserByUid(user_iidd);
		//realUser = userService.getRealUserByUid(user_iidd);
		secUserPost = userService.getSecUserPostByUser(user_iidd);
		secSubsyslist = userService.getAllSubsysAsCon();
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_iidd", user_iidd);
		//map.put("role_type", String.valueOf(Constants.ROLE_TYPE_COMMON));
		List<SecRole> secRoleList = userService.getRoleListByUser(map);
		secRoleMap = new ArrayList<Map<String, Object>>();
		for (SecSubsys secSubsys : secSubsyslist) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			List<SecRole> tempRoleList = new ArrayList<SecRole>();
			for (SecRole secRole : secRoleList) {
				if (secRole.getSubsys_code().equalsIgnoreCase(secSubsys.getSubsys_code())) {
					tempRoleList.add(secRole);
				}
			}
			tempMap.put("subsys_code", secSubsys.getSubsys_code());
			tempMap.put("secRoleList", tempRoleList);
			secRoleMap.add(tempMap);
		}
		return SUCCESS;
	}
}
