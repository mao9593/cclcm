package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 管理特殊打印任务列表
 * 
 * @author guojiao
 * 
 */

public class ManageSpecialPrintEventAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String paper_name = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private List<ProcessJob> jobList = null;
	private JobTypeEnum jobType = JobTypeEnum.SPECIAL_PRINT;

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public String getPaper_name() {
		return paper_name;
	}

	public void setPaper_name(String paper_name) {
		this.paper_name = paper_name;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
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

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
	}

	public String getActionContext() {
		return "/print/managespecialprintevent.action";
	}

	public String getJobType_code() {
		return jobType.getJobTypeCode();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("seclv_code", seclv_code);
		map.put("jobType_code", jobType);
		map.put("job_status", job_status);
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		totalSize = basicService.getJobSize(map); // ledgerService.getDestroyPaperSize
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		jobList = basicService.getJobList(map, rbs);

		for (ProcessJob job : jobList) {
			String event_names = "";
			List<OaPrintEvent> events = printService.getSpecialPrintEventListByJobCode(job.getJob_code());
			for (OaPrintEvent event : events) {
				event_names += event.getPaper_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SPECIAL_PRINT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SPECIAL_PRINT", 3);

		return SUCCESS;
	}

}
