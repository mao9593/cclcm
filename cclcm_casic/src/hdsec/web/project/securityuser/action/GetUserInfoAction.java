package hdsec.web.project.securityuser.action;

import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.securityuser.model.SysUserPost;
import hdsec.web.project.securityuser.model.UserSeclvChangeEvent;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.UserSecurity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新增涉密人员表单查询用户信息
 * 
 * @author guojiao
 * 
 */
public class GetUserInfoAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String useid = "";
	private String func = "";
	private List<BmRealUser> bmUserList = null;
	private BmRealUser bmUser = null;
	private List<UserSeclvChangeEvent> eventlist = null;
	private UserSeclvChangeEvent event = null;
	private SecUser secUser = null;

	public SecUser getSecUser() {
		return secUser;
	}

	public String getUseid() {
		return useid;
	}

	public void setUseid(String useid) {
		this.useid = useid;
	}

	public List<BmRealUser> getBmUserList() {
		return bmUserList;
	}

	public void setBmUserList(List<BmRealUser> bmUserList) {
		this.bmUserList = bmUserList;
	}

	public BmRealUser getBmUser() {
		return bmUser;
	}

	public void setBmUser(BmRealUser bmUser) {
		this.bmUser = bmUser;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
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
		bmUserList = securityUserService.getAllUserInfoList(map);
		if (bmUserList.size() == 0 || useid.isEmpty()) {
			return "noinfo";
		}
		bmUser = bmUserList.get(0);
		secUser = userService.getSecUserByUid(useid);
		
		eventlist = securityUserService.getUSeclvChangeEventList(map);
		if(eventlist != null && eventlist.size() > 0){
			setEvent(eventlist.get(0));
		}	
		return SUCCESS;
	}

}
