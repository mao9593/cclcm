package hdsec.web.project.securityuser.action;

import hdsec.web.project.securityuser.model.ResignEvent;
import hdsec.web.project.securityuser.model.UserAbroadEvent;
import hdsec.web.project.securityuser.model.UserEntrustEvent;
import hdsec.web.project.securityuser.model.UserSeclvChangeEvent;

import java.util.List;

/**
 * 撤销涉密人员申请流程
 * 
 * @author gaoximin 2015-10-12
 */
public class DelSecUserEventAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String type = "";
	private String style = "";
	private String job_code = "";
	private String module = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public String executeFunction() throws Exception {
		if (style.equals("USERSECLV_ADD") && job_code != null) {
			List<UserSeclvChangeEvent> userSeclvChangeEvent = securityUserService
					.getUSeclvChangeEventListByJobCode(job_code);
			for (UserSeclvChangeEvent event : userSeclvChangeEvent) {
				securityUserService.delUSecAddEventByEventCode(event.getEvent_code());
				insertCommonLog("删除新增涉密人员申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("USERSECLV_CHANGE")) {
			List<UserSeclvChangeEvent> userSeclvChangeEvent = securityUserService
					.getUSeclvChangeEventListByJobCode(job_code);
			for (UserSeclvChangeEvent event : userSeclvChangeEvent) {
				securityUserService.delUSecChangeEventByEventCode(event.getEvent_code());
				insertCommonLog("删除涉密人员变更申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("RESIGN")) {
			List<ResignEvent> resignEvent = securityUserService.getUResignEventListByJobCode(job_code);
			for (ResignEvent event : resignEvent) {
				securityUserService.delUResignEventByEventCode(event.getEvent_code());
				insertCommonLog("删除涉密人员脱密(离岗)申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("SECUSER_ABROAD")) {
			List<UserAbroadEvent> userAbroadEvent = securityUserService.getUAbroadEventListByJobCode(job_code);
			for (UserAbroadEvent event : userAbroadEvent) {
				securityUserService.delUAbroadEventByEventCode(event.getEvent_code());
				insertCommonLog("删除用户因私出国申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("SECUSER_ENTRUST")) {
			List<UserEntrustEvent> userEntrustEvent = securityUserService.getUserEntrustEventListByJobCode(job_code);
			for (UserEntrustEvent event : userEntrustEvent) {
				securityUserService.delUserEntrustEventByEventCode(event.getEvent_code());
				insertCommonLog("删除用户委托保密管理申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		}
		return null;
	}
}
