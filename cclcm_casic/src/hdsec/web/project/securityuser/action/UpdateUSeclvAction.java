package hdsec.web.project.securityuser.action;

import hdsec.web.project.securityuser.model.UserSeclvChangeEvent;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 变更用户涉密等级操作
 * 
 * @author gaoximin 2015-6-16
 */
public class UpdateUSeclvAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private UserSeclvChangeEvent event = null;
	private String change_user_iidd = "";
	private String seclv_code_after = null;
	private String dept_id_after = "";
	private String post_id_after = "";

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		event = securityUserService.getUSeclvChangeEventByEventCode(event_code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("oper_user_iidd", getCurUser().getUser_iidd());
		map.put("oper_dept_id", getCurUser().getDept_id());
		map.put("change_time", new Date());
		map.put("change_status", 1);
		change_user_iidd = event.getChange_user_iidd();
		seclv_code_after = event.getSeclv_code_after();
		dept_id_after = event.getDept_id_after();
		post_id_after = event.getPost_id_after();
		securityUserService.UpdateUSeclvByEventCode(map, change_user_iidd, seclv_code_after, dept_id_after,
				post_id_after);
		if (event != null) {
			insertCommonLog("变更用户[" + event.getChange_user_name() + "]涉密等级");
		}
		return "ok";
	}
}
