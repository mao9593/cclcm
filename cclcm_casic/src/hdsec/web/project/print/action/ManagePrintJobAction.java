package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 管理打印任务
 * 
 * @author renmingfei
 * 
 */
public class ManagePrintJobAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	// private Date startTime = TimeUtil.getBeforeXDay(5);
	// private Date endTime = TimeUtil.getCurrentTimestamp();
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private String cycle_type = "";
	private List<ProcessJob> jobList = null;

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

	public String getCycle_type() {
		return cycle_type;
	}

	public void setCycle_type(String cycle_type) {
		this.cycle_type = cycle_type;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public String getActionContext() {
		return "/print/manageprintjob.action";
	}

	public String getJobType_code() {
		return JobTypeEnum.PRINT_REMAIN.getJobTypeCode();
	}

	@Override
	public String executeFunction() throws Exception {
		jobList = new ArrayList<ProcessJob>();
		List<ProcessJob> tempJobList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("job_status", job_status);
		map.put("isPrintJob", true);
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		totalSize = basicService.getJobSize(map);
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		if (cycle_type.isEmpty()) {/*
									 * map.put("jobType_code", JobTypeEnum.PRINT_REMAIN.getJobTypeCode()); tempJobList =
									 * basicService.getJobList(map); if (tempJobList != null) {
									 * jobList.addAll(tempJobList); tempJobList.clear(); } map.put("jobType_code",
									 * JobTypeEnum.PRINT_FILE.getJobTypeCode()); tempJobList =
									 * basicService.getJobList(map); if (tempJobList != null) {
									 * jobList.addAll(tempJobList); tempJobList.clear(); } map.put("jobType_code",
									 * JobTypeEnum.PRINT_SEND.getJobTypeCode()); tempJobList =
									 * basicService.getJobList(map); if (tempJobList != null) {
									 * jobList.addAll(tempJobList); tempJobList.clear(); }
									 */
			// map.put("isPrintJob", true);
			tempJobList = basicService.getJobList(map, rbs);
			if (tempJobList != null) {
				jobList.addAll(tempJobList);
				tempJobList.clear();
			}
		} else {
			map.put("jobType_code", JobTypeEnum.valueOf("PRINT_" + cycle_type));
			tempJobList = basicService.getJobList(map, rbs);
			if (tempJobList != null) {
				jobList.addAll(tempJobList);
				tempJobList.clear();
			}
		}
		for (ProcessJob job : jobList) {
			String event_names = "";
			Integer temp_print = 1;
			Integer tag = 0;
			List<PrintEvent> events = printService.getPrintEventListByJobCode(job.getJob_code());
			for (PrintEvent event : events) {
				event_names += event.getFile_title() + "  ";
				if (event.getPrint_status() == 0) {
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
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PRINT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PRINT", 3);
		return SUCCESS;
	}
}