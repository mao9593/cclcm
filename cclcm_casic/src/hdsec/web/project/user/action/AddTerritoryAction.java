package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecTerritory;

import org.springframework.util.StringUtils;

/**
 * 添加地区
 * @author renmingfei
 *
 */
public class AddTerritoryAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private String terr_code = "";
	private String terr_name = "";
	private String terr_desc = "";
	
	public void setTerr_code(String terr_code) {
		this.terr_code = terr_code.trim();
	}
	
	public void setTerr_name(String terr_name) {
		this.terr_name = terr_name;
	}
	
	public void setTerr_desc(String terr_desc) {
		this.terr_desc = terr_desc;
	}
	
	@Override
	public String executeFunction() throws Exception {
		SecTerritory secTerr = null;
		if (StringUtils.hasLength(terr_code)) {
			secTerr = userService.getSecTerrByCode(terr_code);
			if (secTerr != null) {
				throw new Exception("地区编号已经存在，请更换");
			} else {
				secTerr = new SecTerritory();
				secTerr.setTerr_code(terr_code);
				secTerr.setTerr_name(terr_name);
				secTerr.setTerr_desc(terr_desc);
				userService.addTerritory(secTerr);
				insertAdminLog("添加部门地区:" + terr_name);
				return "insert";
			}
		}
		return SUCCESS;
	}
}
