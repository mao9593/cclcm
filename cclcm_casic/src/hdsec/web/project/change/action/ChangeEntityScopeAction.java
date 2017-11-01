package hdsec.web.project.change.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.change.mapper.ChangeMapper;
import hdsec.web.project.change.model.EventChange;
import hdsec.web.project.user.model.SecUser;

import javax.annotation.Resource;

/**
 * 变换载体归属
 * 
 * @author lixiang
 * 
 */

public class ChangeEntityScopeAction extends ChangeBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String barcode = "";

	@Resource
	private ChangeMapper changeMapper;

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String executeFunction() throws Exception {

		EventChange event = changeMapper.getChangeEventByCode(event_code);

		if (event.getChange_type().equals("toPERSON")) {
			ProcessJob processJob = changeMapper.getProcessJobByJobCode(event.getJob_code());
			SecUser user = userService.getSecUserByUid(processJob.getAccept_user_iidd());
			changeService.changeEntityScope(event_code, user.getUser_iidd(), user.getUser_name(), user.getDept_id(),
					user.getDept_name());
		} else {
			changeService.changeEntityScope(event_code, curUser.getUser_iidd(), curUser.getUser_name(),
					curUser.getDept_id(), curUser.getDept_name());
		}

		insertCommonLog("变更载体[" + barcode + "]归属");
		return null;
	}
}
