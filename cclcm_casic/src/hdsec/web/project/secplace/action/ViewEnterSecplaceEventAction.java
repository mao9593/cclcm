package hdsec.web.project.secplace.action;

import hdsec.web.project.secplace.model.EnterSecplaceEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看进入涉密场所申请列表
 * 
 * @author liuyaling 2015-6-12
 */
public class ViewEnterSecplaceEventAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date enter_time = null;
	private String seclv_code = "";
	private String secplace_name = "";
	private String secplace_code = "";
	private String accompany_id = "";
	private String accompany_name = "";
	private String job_status = "";
	private List<EnterSecplaceEvent> eventList = null;

	public List<EnterSecplaceEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<EnterSecplaceEvent> eventList) {
		this.eventList = eventList;
	}

	public String getAccompany_id() {
		return accompany_id;
	}

	public void setAccompany_id(String accompany_id) {
		this.accompany_id = accompany_id;
	}

	public String getAccompany_name() {
		return accompany_name;
	}

	public void setAccompany_name(String accompany_name) {
		this.accompany_name = accompany_name;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getSecplace_code() {
		return secplace_code;
	}

	public void setSecplace_code(String secplace_code) {
		this.secplace_code = secplace_code;
	}

	public String getEnter_time() {
		return enter_time == null ? "" : sdf.format(enter_time);
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getSecplace_name() {
		return secplace_name;
	}

	public void setSecplace_name(String secplace_name) {
		this.secplace_name = secplace_name;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("seclv_code", seclv_code);
		map.put("enter_time", enter_time);
		map.put("secplace_code", secplace_code);
		map.put("accompany_id", accompany_id);
		map.put("job_status", job_status);
		eventList = secplaceService.getEnterSecplaceEventList(map);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "ENTER_SECPLACE", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "ENTER_SECPLACE", 3);
		return SUCCESS;
	}

}