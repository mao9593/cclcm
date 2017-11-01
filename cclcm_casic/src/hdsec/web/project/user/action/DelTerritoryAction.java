package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecTerritory;

/**
 * 删除地区
 * @author renmingfei
 *
 */
public class DelTerritoryAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String terr_code = "";
	
	public void setTerr_code(String terr_code) {
		this.terr_code = terr_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		SecTerritory secTerr = userService.getSecTerrByCode(terr_code);
		if (secTerr != null && secTerr.getDept_count() > 0) {
			throw new Exception("地区正在使用，无法删除");
		} else {
			String terr_name = userService.getSecTerrByCode(terr_code).getTerr_name();
			userService.delTerrByCode(terr_code);
			insertAdminLog("删除部门地区:" + terr_name);
		}
		return SUCCESS;
	}
}
