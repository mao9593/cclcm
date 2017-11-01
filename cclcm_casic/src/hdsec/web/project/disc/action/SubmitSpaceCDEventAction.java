package hdsec.web.project.disc.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 提交空白盘领用作业
 * 
 * @author lishu 2015-12-11
 */
public class SubmitSpaceCDEventAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String seclv_code = "";
	private List<SpaceCDEvent> eventList = null;
	private final JobTypeEnum jobType = JobTypeEnum.SPACECD_BORROW;

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<SpaceCDEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<SpaceCDEvent> eventList) {
		this.eventList = eventList;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getImportSecLevelByUser(getCurUser().getUser_iidd());
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("apply_user_iidd", getCurUser().getUser_iidd());
		eventList = discService.getSpaceCDEventList(map);
		return SUCCESS;
	}
}
