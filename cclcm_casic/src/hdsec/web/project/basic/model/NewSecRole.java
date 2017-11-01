package hdsec.web.project.basic.model;

import hdsec.web.project.user.model.SecRole;

/**
 * 说明: 继承角色类
 * 
 * @author lixiang
 * 
 */
public class NewSecRole extends SecRole {
	private DeptAdminConfig deptAdminConfig = null;

	public DeptAdminConfig getDeptAdminConfig() {
		return deptAdminConfig;
	}

	public void setDeptAdminConfig(DeptAdminConfig deptAdminConfig) {
		this.deptAdminConfig = deptAdminConfig;
	}

}