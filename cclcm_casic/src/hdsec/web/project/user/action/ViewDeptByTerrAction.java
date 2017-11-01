package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecDept;

import java.util.List;

/**
 * 通过地区查看该地区下的机构列表
 * @author renmingfei
 *
 */
public class ViewDeptByTerrAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String terr_code = null;
	private List<SecDept> deptList = null;
	
	public List<SecDept> getDeptList() {
		return deptList;
	}
	
	public void setTerr_code(String terr_code) {
		this.terr_code = terr_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		deptList = userService.getSecDeptByTerr(terr_code);
		return SUCCESS;
	}
}
