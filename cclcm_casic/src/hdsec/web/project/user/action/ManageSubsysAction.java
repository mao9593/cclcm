package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecSubsys;

import java.util.List;

public class ManageSubsysAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SecSubsys> subsysList = null;
	private Integer admin_version = 100;

	public List<SecSubsys> getSubsysList() {
		return subsysList;
	}

	public Integer getAdmin_version() {
		return admin_version;
	}

	public void setAdmin_version(Integer admin_version) {
		this.admin_version = admin_version;
	}

	@Override
	public String executeFunction() throws Exception {
		subsysList = userService.getAllSubsys();
		// 三员用户管理权限转换
		if (admin_version == 0) {
			userService.delSysConfigAdminVersion();
		} else if (admin_version == 1) {
			userService.insertSysConfigAdminVersion();
		}
		// 判断当前三员用户管理权限划分版本（集团版本、保密局测评版本）
		admin_version = userService.getSysConfigAdminVersion();
		return SUCCESS;
	}
}
