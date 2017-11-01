package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecLevel;

/**
 * 启用已停用的密级
 * 
 * @author lixiang
 * 
 */
public class RecoverSeclvAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer seclv_code = null;
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		SecLevel seclv = userService.getSecLevelByCode(seclv_code);
		if (seclv == null) {
			dealException(new Exception("该密级不存在"));
		} else {
			String seclv_name = userService.getSecLevelByCode(seclv_code).getSeclv_name();
			userService.recoverSecLvByCode(seclv_code);
			insertAdminLog("启用密级:" + seclv_name);
		}
		return SUCCESS;
	}
}
