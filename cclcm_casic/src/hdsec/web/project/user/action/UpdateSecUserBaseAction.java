package hdsec.web.project.user.action;

import hdsec.web.project.user.model.Post;
import hdsec.web.project.user.model.RealUser;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.UserSecurity;

import java.util.ArrayList;
import java.util.List;

/**
 * 展示用户基本信息，用于修改页面
 * 
 * @author renmingfei
 * 
 */
public class UpdateSecUserBaseAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_iidd = "";
	private String dept_id = "";
	private String position = "";
	private Integer beforeCount = null;
	private Integer behindCount = null;
	private SecUser secUser = null;
	private List<Post> postList = new ArrayList<Post>();
	private RealUser realUser = null;
	
	public String getUser_iidd() {
		return user_iidd;
	}
	
	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}
	
	public String getDept_id() {
		return dept_id;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public Integer getBeforeCount() {
		return beforeCount;
	}
	
	public void setBeforeCount(Integer beforeCount) {
		this.beforeCount = beforeCount;
	}
	
	public Integer getBehindCount() {
		return behindCount;
	}
	
	public void setBehindCount(Integer behindCount) {
		this.behindCount = behindCount;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public SecUser getSecUser() {
		return secUser;
	}
	
	public List<Post> getPostList() {
		return postList;
	}
	
	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}
	
	public RealUser getRealUser() {
		return realUser;
	}
	
	@Override
	public String executeFunction() throws Exception {
		secUser = userService.getSecUserByUid(user_iidd);
		postList = userService.getPostByDeptAsCon(secUser.getDept_id());
		// 本版暂时不需要此类，realuser里的信息，secuser里都有，暂时只使用secuser
		// realUser = userService.getRealUserByUid(user_iidd);
		return SUCCESS;
	}
}
