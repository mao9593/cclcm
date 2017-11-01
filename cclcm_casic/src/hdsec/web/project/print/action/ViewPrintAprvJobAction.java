package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看已审批打印任务列表
 * 
 * @author renmingfei
 * 
 */
public class ViewPrintAprvJobAction extends PrintBaseAction {

	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;
	private String type = "";
	private String file_scope="";
	private String seclv_accord="";
	private String secret_time="";

	public String getFile_scope() {
		return file_scope;
	}

	public void setFile_scope(String file_scope) {
		this.file_scope = file_scope;
	}

	public String getSeclv_accord() {
		return seclv_accord;
	}

	public void setSeclv_accord(String seclv_accord) {
		this.seclv_accord = seclv_accord;
	}

	public String getSecret_time() {
		return secret_time;
	}

	public void setSecret_time(String secret_time) {
		this.secret_time = secret_time;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name.trim();
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		List<ProcessJob> tempList = null;
		jobList = new ArrayList<ProcessJob>();
		tempList = basicService.getApprovedJobByUserIdPrint(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_FILE, user_name,
				seclv_code,file_scope,seclv_accord,secret_time);
		jobList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserIdPrint(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_REMAIN,
				user_name, seclv_code,file_scope,seclv_accord,secret_time);
		jobList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserIdPrint(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_SEND, user_name,
				seclv_code,file_scope,seclv_accord,secret_time);
		jobList.addAll(tempList);
		tempList = basicService.getApprovedJobByUserIdPrint(getCurUser().getUser_iidd(), JobTypeEnum.SPECIAL_PRINT,
				user_name, seclv_code,file_scope,seclv_accord,secret_time);
		jobList.addAll(tempList);

		for (ProcessJob job : jobList) {
			String event_names = "";
			if (job.getJobType().getJobTypeCode().equals("SPECIAL_PRINT")) {
				List<OaPrintEvent> events = printService.getSpecialPrintEventListByJobCode(job.getJob_code());
				for (OaPrintEvent event : events) {
					event_names += event.getPaper_name() + "  ";
				}
			} else {
				List<PrintEvent> events = printService.getPrintEventListByJobCode(job.getJob_code());
				for (PrintEvent event : events) {
					event_names += event.getFile_title() + "  ";
				}
			}
			job.setEvent_names(event_names);
		}
		return SUCCESS;
	}

}
