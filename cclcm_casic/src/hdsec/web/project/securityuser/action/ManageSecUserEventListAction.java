package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分模块申请记录合并
 * 
 * @author gaoximin 2015-10-13
 */
public class ManageSecUserEventListAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String seclv_code = "";
	private String jobType_code = "";
	private String module = "";// 空默认为人员模块，其他根据参数判定模块（保密处管理、其他保密管理、宣传报道及提供资料管理）
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

	public String getJobType_code() {
		return jobType_code;
	}

	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	@Override
	public String executeFunction() throws Exception {
		jobList = new ArrayList<ProcessJob>();
		List<ProcessJob> tempJobList = null;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("dept_id", getCurUser().getDept_id());
		map.put("seclv_code", seclv_code);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("job_status", job_status);
		map.put("jobType_code", jobType_code);
		if (module.equals("")) {
			// 涉密人员
			map.put("module", "secuser");
		} else if (module.equals("secmanageBMC")) {
			// 保密处管理
			map.put("module", "secmanageBMC");
		} else if (module.equals("secmanage")) {
			// 其他保密管理
			map.put("module", "secmanage");
		} else if (module.equals("publicity")) {
			// 宣传报道管理
			map.put("module", "publicity");
		}

		tempJobList = securityUserService.getJobList(map);
		if (tempJobList != null) {
			jobList.addAll(tempJobList);
			tempJobList.clear();
		}
		if (module.equals("")) {
			// 涉密人员
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_ADD", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_ADD", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_CHANGE", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSECLV_CHANGE", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "RESIGN", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "RESIGN", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SECUSER_ABROAD", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SECUSER_ABROAD", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SECUSER_ENTRUST", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SECUSER_ENTRUST", 3);
			return SUCCESS;
		} else if (module.equals("secmanageBMC")) {
			// 保密处管理
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_DEPT", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_DEPT", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_SECCHECK", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_SECCHECK", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_RECTIFY", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PUNISH_RECTIFY", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SEC_CHECK", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SEC_CHECK", 3);
			return "secmanageBMC";
		} else if (module.equals("secmanage")) {
			// 其他保密管理
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "FIELDIN", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "FIELDIN", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "FILEOUTMAKE", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "FILEOUTMAKE", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSEC_ACTIVITY", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "USERSEC_ACTIVITY", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "ENTER_SECPLACE", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "ENTER_SECPLACE", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "OUT_EXCHANGE", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "OUT_EXCHANGE", 3);
			return "secmanage";
		} else if (module.equals("publicity")) {
			// 宣传报道及提供资料管理
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT2", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT2", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT3", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_REPORT3", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_DEPTREPORT", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_DEPTREPORT", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTRAPUBL", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTRAPUBL", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTERPUBL", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EVENT_INTERPUBL", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "MATERIAL", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "MATERIAL", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "INTER_EMAIL", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "INTER_EMAIL", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EXHIBITION", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "EXHIBITION", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPERPATENT", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPERPATENT", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_OTHERS", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_OTHERS", 3);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_RESEARCH", 2);
			basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER_RESEARCH", 3);
			return "publicity";

		}
		return SUCCESS;
	}
}
