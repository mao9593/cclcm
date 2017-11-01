package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.print.model.PrintEvent;

import java.util.List;

/**
 * 查看打印任务详情
 * 
 * @author renmingfei
 * 
 */
public class ViewPrintJobDetailAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private String is_prop = "N";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<PrintEvent> eventList = null;
	private List<OaPrintEvent> oaList = null;
	private String proxyoutput_user_name = "";
	private String view_file_scope = "";
	private String view_seclv_accord = "";
	private String view_secret_time = "";
	private String viewflag = "N";

	public String getView_file_scope() {
		return view_file_scope;
	}

	public String getView_seclv_accord() {
		return view_seclv_accord;
	}

	public String getView_secret_time() {
		return view_secret_time;
	}

	public String getViewflag() {
		return viewflag;
	}

	public void setViewflag(String viewflag) {
		this.viewflag = viewflag;
	}

	public String getProxyoutput_user_name() {
		return proxyoutput_user_name;
	}

	public void setProxyoutput_user_name(String proxyoutput_user_name) {
		this.proxyoutput_user_name = proxyoutput_user_name;
	}

	public List<OaPrintEvent> getOaList() {
		return oaList;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public List<PrintEvent> getEventList() {
		return eventList;
	}

	public String getIs_prop() {
		return is_prop;
	}

	public void setIs_prop(String is_prop) {
		this.is_prop = is_prop;
	}

	@Override
	public String executeFunction() throws Exception {
		job = basicService.getProcessJobByCode(job_code);
		process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
		ProcessRecord record = new ProcessRecord();
		record.setJob_code(job_code);
		recordList = activitiService.getProcessRecordList(record);
		if (job.getJobType().getJobTypeCode().equals("SPECIAL_PRINT")) {
			oaList = printService.getSpecialPrintEventListByJobCode(job_code);
			return "special";
		} else {
			eventList = printService.getPrintEventListByJobCode(job_code);
			// 获取打印代理人或刻录代理人
			String proxyprint_user_iidd = basicService.getPrintProxyUserIdByJobcode(job_code);
			if (proxyprint_user_iidd != null && !proxyprint_user_iidd.equals("")) {
				setProxyoutput_user_name(userService.getUserNameByUserId(proxyprint_user_iidd));
			}
			// 获取定密依据信息列表
			view_file_scope = eventList.get(0).getFile_scope();
			view_seclv_accord = eventList.get(0).getSeclv_accord();
			view_secret_time = eventList.get(0).getSecret_time();
			if (!view_file_scope.equals("") && !view_seclv_accord.equals("") && !view_secret_time.equals("")) {
				setViewflag("Y");
			}
		}
		return SUCCESS;
	}
}
