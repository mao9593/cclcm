package hdsec.web.project.disc.action;

import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 空白盘录入记录
 * 
 * @author zp
 * @author lidepeng(20150811)
 */

/**
 * 
 * @author lishu 2015-12-14
 */
public class ManageSpaceCDEnterListJobAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String seclv_code = "";
	private List<SpaceCDEvent> spacecds = null;

	public List<SpaceCDEvent> getSpacecds() {
		return spacecds;
	}

	public void setSpacecds(List<SpaceCDEvent> spacecds) {
		this.spacecds = spacecds;
	}

	public String getStartTime_str() {
		return startTime == null ? "" : sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime_str() {
		return endTime == null ? "" : sdf.format(endTime);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		spacecds = discService.getEventSpaceCdList(map);
		return SUCCESS;
	}
}