package hdsec.web.project.user.model;

import java.util.Collections;
import java.util.List;

public class Role {
	private String roleId = "";
	private String roleName = "";
	private List<String> permissionList;

	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Role(String roleId, String roleName, List<String> permissionList) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		if (permissionList != null)
			this.permissionList = permissionList;
		else
			this.permissionList = Collections.emptyList();
	}

	public Role() {
	}

}
