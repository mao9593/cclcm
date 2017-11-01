package hdsec.web.project.securityuser.action;


import hdsec.web.project.securityuser.model.SysUserPost;
import hdsec.web.project.securityuser.model.UserSeclvChangeEvent;
import hdsec.web.project.user.model.UserSecurity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 涉密人员变更表单查询用户信息
 * 
 * @author gaoyiming
 * 
 */
public class GetChangeInfoAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String useid = "";
	private List<UserSeclvChangeEvent> eventlist = null;
	private UserSeclvChangeEvent event = null;


	public String getUseid() {
		return useid;
	}

	public void setUseid(String useid) {
		this.useid = useid;
	}
	
	public UserSeclvChangeEvent getEvent() {
		return event;
	}

	public void setEvent(UserSeclvChangeEvent event) {
		this.event = event;
	}

	public List<UserSeclvChangeEvent> getEventlist() {
		return eventlist;
	}

	public void setEventlist(List<UserSeclvChangeEvent> eventlist) {
		this.eventlist = eventlist;
	}
	
	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}
	
	public List<SysUserPost> getPostList() {
		return securityUserService.getPostList();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", useid);
		map.put("info_type", "2");
		
		eventlist = securityUserService.getUSeclvChangeEventList(map);
		event = eventlist.get(0);

		return SUCCESS;
	}


}

