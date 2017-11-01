package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecTerritory;

public class UpdateTerritoryAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private SecTerritory secTerr = null;
	private String terr_code = "";
	private String terr_name = "";
	private String terr_desc = "";
	private String update = "N";
	
	public String getTerr_code() {
		return terr_code;
	}
	
	public void setTerr_code(String terr_code) {
		this.terr_code = terr_code;
	}
	
	public SecTerritory getSecTerr() {
		return secTerr;
	}
	
	public void setTerr_name(String terr_name) {
		this.terr_name = terr_name;
	}
	
	public void setTerr_desc(String terr_desc) {
		this.terr_desc = terr_desc;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {//处理修改操作
			secTerr = new SecTerritory();
			secTerr.setTerr_code(terr_code);
			secTerr.setTerr_name(terr_name);
			secTerr.setTerr_desc(terr_desc);
			userService.updateTerritory(secTerr);
			insertAdminLog("修改部门地区:" + terr_code);
			return "update";
		} else {
			secTerr = userService.getSecTerrByCode(terr_code);
		}
		return SUCCESS;
	}
}
