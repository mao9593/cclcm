package hdsec.web.project.disc.action;

import org.springframework.util.StringUtils;

public class DelMultiSpaceCDEventAction extends DiscBaseAction {

	private static final long serialVersionUID = 1L;

	private String event_delids = "";

	public String getEvent_delids() {
		return event_delids;
	}

	public void setEvent_delids(String event_delids) {
		this.event_delids = event_delids;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_delids)) {
			String[] ids = event_delids.split(":");
			for (String id : ids) {
				logger.info("id:" + id);
				discService.delSpaceCDEventById(id);
				insertCommonLog("删除空白盘作业[" + id + "]");
			}
		}
		return "ok";
	}

}
