package hdsec.web.project.input.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.input.model.InputEvent;
import hdsec.web.project.input.model.InputFile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 电子文件导入详细
 * 
 * @author guoxh 2016-10-8 18:30:03
 */
public class ViewInputJobDetailAction extends InputBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private String event_code = "";
	private String is_prop = "N";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<InputFile> eventList = null;
	private String storePath = "";
	private List<InputFile> fileLists = null;
	private InputEvent event = null;
	private String current_user_iidd = "";// 当前用户id

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getIs_prop() {
		return is_prop;
	}

	public void setIs_prop(String is_prop) {
		this.is_prop = is_prop;
	}

	public ProcessJob getJob() {
		return job;
	}

	public void setJob(ProcessJob job) {
		this.job = job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public void setProcess(ApproveProcess process) {
		this.process = process;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<ProcessRecord> recordList) {
		this.recordList = recordList;
	}

	public List<InputFile> getEventList() {
		return eventList;
	}

	public void setEventList(List<InputFile> eventList) {
		this.eventList = eventList;
	}

	public String getEvent_code() {
		return event_code;
	}

	public InputEvent getEvent() {
		return event;
	}

	public void setEvent(InputEvent event) {
		this.event = event;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	public List<InputFile> getFileLists() {
		return fileLists;
	}

	public void setFileLists(List<InputFile> fileLists) {
		this.fileLists = fileLists;
	}

	public String getCurrent_user_iidd() {
		return current_user_iidd;
	}

	@Override
	public String executeFunction() throws Exception {
		event = inputService.getInputEventByCode(event_code);
		current_user_iidd = getCurUser().getUser_iidd();
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String[] filelist = event.getFile_list().split(":");
			String[] fileseclevel = event.getFile_seclevel().split(":");
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				storePath = PropertyUtil.getInputFileStorePath();
				eventList = new ArrayList<InputFile>();
				Integer seclv_code;
				String seclv_name = "";
				String file_path = "";
				for (int i = 0; i < filelist.length; i++) {
					seclv_code = Integer.parseInt(fileseclevel[i].trim());
					seclv_name = userService.getSecLevelByCode(seclv_code).getSeclv_name();
					file_path = storePath + "/" + event_code + "/" + MD5.getStringMD5(filelist[i]);
					eventList.add(new InputFile(filelist[i], seclv_code, seclv_name, file_path));
				}
			}
			job_code = inputService.getJobCodeByEventCode(event_code);
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
			}
			return SUCCESS;
		}
	}
}
