package hdsec.web.project.passport.action;

import hdsec.web.project.passport.model.EntityPassport;

import org.springframework.util.StringUtils;

public class ViewPassportDetailAction extends PassportBaseAction {

	private static final long serialVersionUID = 1L;
	private EntityPassport passport = null;
	private String passport_id = ""; // 护照编号

	public EntityPassport getPassport() {
		return passport;
	}

	public void setPassport(EntityPassport passport) {
		this.passport = passport;
	}

	public String getPassport_id() {
		return passport_id;
	}

	public void setPassport_id(String passport_id) {
		this.passport_id = passport_id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(passport_id)) {
			passport = passportService.getPassportById(passport_id);
			return SUCCESS;
		} else {
			throw new Exception("无法查询护照信息，请重新尝试。");
		}
	}
}
