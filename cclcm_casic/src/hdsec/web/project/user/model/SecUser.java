package hdsec.web.project.user.model;

import hdsec.web.project.common.util.Constants;

import java.util.List;

public class SecUser extends RealUser {
	protected String user_iidd = "";
	protected String user_ppww = "";
	protected String user_name = "";
	protected String real_user_id = "";
	protected Integer status = null;
	protected String post_id = null;
	protected Integer user_type = null;
	protected String idCard = "";// 身份证号
	protected String pass_num = "";// 用户卡号
	protected Integer print_method = 0;// 是否独立模式
	protected Integer print_permission = 0;// 可否本地打印
	protected String security_code = "";// 涉密人员类别
	protected String ext_code = "";
	protected String post_name = "";
	protected String dept_id = "";
	protected String dept_cs = "";
	protected String dept_name = "";
	protected String terr_code = "";
	protected String terr_name = "";
	protected String security_name = "";
	protected List<String> allOper = null;
	protected List<String> nonOper = null;
	protected List<SecRole> specialRole = null;
	protected List<String> specialOper = null;
	protected List<SecRole> userRoleList = null;
	protected UserSecurity security = null;
	protected boolean need_checkPwd = true;
	protected String del_status = "";// 安全用户封存状态
	protected String status_name = "";
	protected Integer rank = 100; // 用户排序值
	private String role_name = "";

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public boolean isNeed_checkPwd() {
		return need_checkPwd;
	}

	public void setNeed_checkPwd(boolean need_checkPwd) {
		this.need_checkPwd = need_checkPwd;
	}

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public String getPass_num() {
		return pass_num;
	}

	public void setPass_num(String pass_num) {
		this.pass_num = pass_num;
	}

	public String getSecurity_name() {
		return security_name;
	}

	public void setSecurity_name(String security_name) {
		this.security_name = security_name;
	}

	public List<SecRole> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<SecRole> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public String getRoleNameStr() {
		String roleName = "";
		if (userRoleList != null && userRoleList.size() > 0) {
			for (SecRole role : userRoleList) {
				roleName = roleName + role.getRole_name() + ",";
			}
			if (roleName.endsWith(",")) {
				roleName = roleName.substring(0, roleName.length() - 1);
			}
			return roleName;
		} else {
			return "";
		}
	}

	public String getAdminIsNull() {
		int count = 0;
		if (userRoleList != null && userRoleList.size() > 0) {
			for (SecRole role : userRoleList) {
				if (role.getRole_name().equals("系统管理员") || role.getRole_name().equals("审计管理员")) {
					count++;
				}
			}
			if (count == 0) {
				return null;
			} else {
				return "admin";
			}
		} else {
			return null;
		}
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_ppww() {
		return user_ppww;
	}

	public void setUser_ppww(String user_ppww) {
		this.user_ppww = user_ppww;
	}

	@Override
	public String getReal_user_id() {
		return real_user_id;
	}

	@Override
	public void setReal_user_id(String real_user_id) {
		this.real_user_id = real_user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getStatus() {
		return status;
	}

	public String getStatus_str() {
		return status == null ? "" : String.valueOf(status);
	}

	public String getStatus_name() {
		if (status == null) {
			return "未知";
		}
		switch (status) {
		case 0:
			return "正常";
		case 1:
			return "锁定";
		default:
			return "未知";
		}
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPost_id_str() {
		return post_id == null ? "" : String.valueOf(post_id);
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public Integer getUser_type() {
		return user_type;
	}

	public void setUser_type(Integer user_type) {
		this.user_type = user_type;
	}

	public String getExt_code() {
		return ext_code;
	}

	public void setExt_code(String ext_code) {
		this.ext_code = ext_code;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_cs() {
		return dept_cs;
	}

	public void setDept_cs(String dept_cs) {
		this.dept_cs = dept_cs;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getTerr_code() {
		return terr_code;
	}

	public void setTerr_code(String terr_code) {
		this.terr_code = terr_code;
	}

	public String getTerr_name() {
		return terr_name;
	}

	public void setTerr_name(String terr_name) {
		this.terr_name = terr_name;
	}

	public List<SecRole> getSpecialRole() {
		return specialRole;
	}

	public void setSpecialRole(List<SecRole> specialRole) {
		this.specialRole = specialRole;
	}

	public List<String> getAllOper() {
		return allOper;
	}

	public void setAllOper(List<String> allOper) {
		this.allOper = allOper;
	}

	public List<String> getNonOper() {
		return nonOper;
	}

	public void setNonOper(List<String> nonOper) {
		this.nonOper = nonOper;
	}

	public List<String> getSpecialOper() {
		return specialOper;
	}

	public void setSpecialOper(List<String> specialOper) {
		this.specialOper = specialOper;
	}

	public UserSecurity getSecurity() {
		return security;
	}

	public void setSecurity(UserSecurity security) {
		this.security = security;
	}

	public String getDel_status() {
		return del_status;
	}

	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}

	public Integer getPrint_method() {
		return print_method;
	}

	public void setPrint_method(Integer print_method) {
		this.print_method = print_method;
	}

	public boolean isPrintMethodStart() {
		return print_method != null && print_method == 1;
	}

	public Integer getPrint_permission() {
		return print_permission;
	}

	public void setPrint_permission(Integer print_permission) {
		this.print_permission = print_permission;
	}

	public boolean isPrintPermissionStart() {
		return print_permission != null && print_permission == 1;
	}

	public String getDeletable() {
		return is_admin() ? "N" : "Y";
	}

	public boolean is_admin() {
		for (SecRole role : userRoleList) {
			if (role.getRole_type() == Constants.ROLE_TYPE_READONLY) {
				return true;
			}
		}
		return false;
	}

	public String getIsAdmin() {
		for (SecRole role : userRoleList) {
			if (role.getRole_type() == Constants.ROLE_TYPE_READONLY) {
				return "Y";
			}
		}
		return "N";
	}

	public boolean is_sysAdmin() {
		for (SecRole role : userRoleList) {
			if (role.getRole_type() == Constants.ROLE_TYPE_READONLY && role.getRole_spec_key().equalsIgnoreCase("SYS")) {
				return true;
			}
		}
		return false;
	}

	public String getIsSysAdmin() {
		for (SecRole role : userRoleList) {
			if (role.getRole_type() == Constants.ROLE_TYPE_READONLY && role.getRole_spec_key().equalsIgnoreCase("SYS")) {
				return "Y";
			}
		}
		return "N";
	}

	public boolean is_secAdmin() {
		for (SecRole role : userRoleList) {
			if (role.getRole_type() == Constants.ROLE_TYPE_READONLY && role.getRole_spec_key().equalsIgnoreCase("SEC")) {
				return true;
			}
		}
		return false;
	}

	public String getIsSecAdmin() {
		for (SecRole role : userRoleList) {
			if (role.getRole_type() == Constants.ROLE_TYPE_READONLY && role.getRole_spec_key().equalsIgnoreCase("SEC")) {
				return "Y";
			}
		}
		return "N";
	}

	public boolean is_audAdmin() {
		for (SecRole role : userRoleList) {
			if (role.getRole_type() == Constants.ROLE_TYPE_READONLY && role.getRole_spec_key().equalsIgnoreCase("AUD")) {
				return true;
			}
		}
		return false;
	}

	public String getIsAudAdmin() {
		for (SecRole role : userRoleList) {
			if (role.getRole_type() == Constants.ROLE_TYPE_READONLY && role.getRole_spec_key().equalsIgnoreCase("AUD")) {
				return "Y";
			}
		}
		return "N";
	}

	public String getCanConfigDept() {
		for (SecRole role : userRoleList) {
			if (role.getRole_spec_key() == null || role.getRole_spec_key().equals("")||role.getRole_spec_key().equals("BURNADMIN")) {
				return "Y";
			}
		}
		return "N";
	}

	public SecUser() {
		super();
	}

	public boolean hasPermission(String permission) {
		// 如果用户的权限列表中包含该权限字符串，则返回true
		if (allOper != null && allOper.contains(permission)) {
			return true;
		} else if (allOper != null && allOper.contains("/" + permission)) {
			return true;
		} else if (nonOper != null && nonOper.contains(permission)) {
			// 如果不包含该串，则要判断是否在不具备权限的列表中
			return false;
		} else if (nonOper != null && nonOper.contains("/" + permission)) {
			return false;
		} else {
			// 数据库操作表中没有此操作记录，默认返回true;
			return true;
		}
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
}
