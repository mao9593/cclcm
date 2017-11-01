package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.user.model.SecRole;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.UserSecurity;
import hdsec.web.project.user.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 查询某部门内的用户
 * 
 * @author renmingfei
 * 
 */
public class ViewUserByDeptAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	private UserService userService;
	private List<SecUser> secUserList;
	private String user_iidd = "";
	private String user_name = "";
	private String com_telephone = "";// 固定电话
	private Boolean isAllDept = null;
	private String status = "";
	private String role_id = "";
	private String dept_id = "";
	private String del_status = "N";
	private String security_code = "";
	private String pass_num = "";
	protected List<SecRole> userRoleList = null;
	private String display_flag = "SEC";
	private Integer admin_version = 0;
	private String dept_ids = "";
	private String no_user_iidd = "";// 用于中物7所载体归属转换 流转人过滤掉内置管理员及三员

	public void setNo_user_iidd(String no_user_iidd) {
		this.no_user_iidd = no_user_iidd;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids;
	}

	public String getDept_ids() {
		return dept_ids;
	}

	public String getPass_num() {
		return pass_num;
	}

	public void setPass_num(String pass_num) {
		this.pass_num = pass_num;
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

	public String getCom_telephone() {
		return com_telephone;
	}

	public void setCom_telephone(String com_telephone) {
		this.com_telephone = com_telephone;
	}

	public Boolean getIsAllDept() {
		return isAllDept;
	}

	public void setIsAllDept(Boolean isAllDept) {
		this.isAllDept = isAllDept;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		if (StringUtils.hasLength(dept_id)) {
			return userService.getDeptNameByDeptId(dept_id);
		} else {
			return "";
		}
	}

	public List<SecRole> getSecRoleList() {
		return userService.getSecRole(new HashMap<String, String>());
	}

	public String getAllDept() {
		return isAllDept == null ? "N" : "Y";
	}

	public String getDel_status() {
		return del_status;
	}

	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}

	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}

	public String getSecurity_code() {
		return security_code;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public String getDisplay_flag() {
		return display_flag;
	}

	public void setDisplay_flag(String display_flag) {
		this.display_flag = display_flag;
	}

	public Integer getAdmin_version() {
		return admin_version;
	}

	public void setAdmin_version(Integer admin_version) {
		this.admin_version = admin_version;
	}

	@Override
	public String executeFunction() throws Exception {
		String pageIndexName = new ParamEncoder("user").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}

		beginIndex = page * pageSize;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isAllDept", isAllDept == null ? "" : "Y");
		map.put("userId", user_iidd);
		map.put("user_name", user_name);
		map.put("no_user_iidd", no_user_iidd);
		map.put("com_telephone", com_telephone);
		map.put("status", status);
		map.put("role_id", role_id);
		map.put("mainDeptId", dept_id);
		map.put("dept_ids", dept_ids.equals("") ? "" : StringUtil.stringArrayToList(dept_ids.split(",")));
		map.put("is_sealed", del_status);
		map.put("security_code", security_code);
		map.put("pass_num", pass_num);
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = userService.getUserByDeptSize(map);
		secUserList = userService.getUserByDept(map, rbs);
		// 判断当前三员用户管理权限划分版本（集团版本、保密局测评版本）
		admin_version = userService.getSysConfigAdminVersion();
		// 判断系统管理员、安全管理员列表操作按钮显示
		userRoleList = getCurrentUser().getUserRoleList();
		if (userRoleList != null) {
			for (SecRole role : userRoleList) {
				if (role.getRole_type() == Constants.ROLE_TYPE_READONLY
						&& role.getRole_spec_key().equalsIgnoreCase("SYS")) {
					display_flag = "SYS";
				}
			}
		}
		return SUCCESS;
	}

	public List<SecUser> getSecUserList() {
		return secUserList;
	}
}
