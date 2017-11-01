package hdsec.web.project.user.model;

import ht304.hdsec.framework.common.model.FrameworkActionUser;

import java.util.List;

public class ActionUser implements FrameworkActionUser {

	private String uid;
	private String password;
	private List<String> permissionList;
	private List<String> permissionTypeList;

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean hasPermission(String permission) {
		for (String s : permissionList) {
			if (s.equals(permission))
				return true;
		}
		return false;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUid() {
		return uid;
	}

	public List<String> getPermissionTypeList() {
		return permissionTypeList;
	}

	public void setPermissionTypeList(List<String> permissionTypeList) {
		this.permissionTypeList = permissionTypeList;
	}

}
