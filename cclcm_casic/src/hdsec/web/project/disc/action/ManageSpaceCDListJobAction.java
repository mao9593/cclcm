package hdsec.web.project.disc.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * 管理空白盘申领任务，空白盘申请列表
 * 
 * @author lishu 2015-12-14
 */
public class ManageSpaceCDListJobAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private List<ProcessJob> jobList = null;
	private String type = "";
	private List<SecLevel> seclvList = null;

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

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public String getJobType_code() {
		return JobTypeEnum.SPACECD_BORROW.getJobTypeCode();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActionContext() {
		return "/disc/managespacecdlistjob.action";
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
		map.put("jobType_code", JobTypeEnum.SPACECD_BORROW.getJobTypeCode());
		map.put("job_status", job_status);
		totalSize = basicService.getJobSize(map);
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);		
		jobList = basicService.getJobList(map, rbs);
		for (ProcessJob job : jobList) {
			String event_names = "";
			Integer temp_print = 1;
			Integer tag = 0;
			List<SpaceCDEvent> events = discService.getSpaceCDEventListByJobCode(job.getJob_code());
			for (SpaceCDEvent event : events) {
				event_names += "";
				if (event.getAssign_status() == 0) {
					temp_print = 0;
					tag++;
				}
			}
			if (tag != events.size()) {
				job.setPrint_num(1);
			} else {
				job.setPrint_num(0);
			}
			job.setEvent_names(event_names);
			job.setPrint_status(temp_print);
		}

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SPACECD_BORROW", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SPACECD_BORROW", 3);

		return SUCCESS;
	}
}