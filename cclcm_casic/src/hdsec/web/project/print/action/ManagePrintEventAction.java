package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理打印作业
 * 
 * @author gaoxm
 * 
 */

public class ManagePrintEventAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String filename = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private Integer print_status = null;
	private List<PrintEvent> eventList = null;
	private JobTypeEnum jobType = JobTypeEnum.PRINT_REMAIN;
	private String proxyprint_user_iidd = "";

	public String getProxyprint_user_iidd() {
		return proxyprint_user_iidd;
	}

	public void setProxyprint_user_iidd(String proxyprint_user_iidd) {
		this.proxyprint_user_iidd = proxyprint_user_iidd;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getSeclv_code() {
		return seclv_code;
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

	public Integer getPrint_status() {
		return print_status;
	}

	public void setPrint_status(Integer print_status) {
		this.print_status = print_status;
	}

	public List<PrintEvent> getEventList() {
		return eventList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if ((proxyprint_user_iidd != null) && (!proxyprint_user_iidd.equals(""))) {
			map.put("proxyprint_user_iidd", proxyprint_user_iidd);
			basicService.setClientMsgRead(proxyprint_user_iidd, "PROXY_PRINT", 2);
			basicService.setClientMsgRead(proxyprint_user_iidd, "PROXY_PRINT", 3);
		} else {
			map.put("user_iidd", getCurUser().getUser_iidd());
		}
		map.put("filename", filename);
		map.put("seclv_code", seclv_code);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("job_status", job_status);
		map.put("print_status", print_status);
		eventList = printService.getPrintEventList(map);
		return SUCCESS;
	}

}
