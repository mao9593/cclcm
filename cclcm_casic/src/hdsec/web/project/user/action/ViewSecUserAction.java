package hdsec.web.project.user.action;

import hdsec.web.project.user.model.Post;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.UserSecurity;
import hdsec.web.project.user.service.UserService;

import java.util.List;

import javax.annotation.Resource;

/**
 * 查询用户
 * 
 * @author renmingfei
 * 
 */
public class ViewSecUserAction extends UserBaseAction {

	private static final long serialVersionUID = 1L;
	@Resource
	private UserService userService;
	private List<SecUser> secUserList;
	private String user_iidd = "";
	private String user_name = "";
	private String base_sex = "";
	private String com_telephone = "";
	private String dept_id = "";
	private String dept_name = "";
	private String pass_num = "";
	private String security_code = "";
	private String status = "";

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getBase_sex() {
		return base_sex;
	}

	public void setBase_sex(String base_sex) {
		this.base_sex = base_sex;
	}

	public String getCom_telephone() {
		return com_telephone;
	}

	public void setCom_telephone(String com_telephone) {
		this.com_telephone = com_telephone;
	}

	public String getPass_num() {
		return pass_num;
	}

	public void setPass_num(String pass_num) {
		this.pass_num = pass_num;
	}

	public List<SecUser> getSecUserList() {
		return secUserList;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}

	@Override
	public String executeFunction() throws Exception {
		SecUser secUser = new SecUser();
		secUser.setUser_iidd(user_iidd);
		secUser.setUser_name(user_name);
		secUser.setBase_sex(base_sex);
		secUser.setCom_telephone(com_telephone);
		secUser.setPass_num(pass_num);
		secUser.setDept_id(dept_id);
		secUser.setDept_name(dept_name);
		secUser.setSecurity_code(security_code);
		if (!"".equals(status)) {
			secUser.setStatus(Integer.parseInt(status));
		}
		// System.out.println("000000000:" + status);
		secUserList = userService.getSecUser(secUser);
		return SUCCESS;
	}

	public List<Post> getPostList() {
		return userService.getAllPostAsCon();
	}
}
