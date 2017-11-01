package hdsec.web.project.enter.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.enter.model.EnterEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

public class ViewEnterEventDetailAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private EnterEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private String storePath = "";
	private List<BurnFile> fileList = null;
	private String current_user_iidd = "";// 当前用户id
	private String time_flag = ""; // 用于标识时间属性, 部门文件录入申请菜单下添加部门文件后,查看详情时科研工作手册为"启用时间"(USETIME),其他为"申请时间"

	public String getTime_flag() {
		return time_flag;
	}

	public void setTime_flag(String time_flag) {
		this.time_flag = time_flag;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getOp() {
		return op;
	}

	public EnterEvent getEvent() {
		return event;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public ProcessJob getJob() {
		return job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public String getStorePath() {
		return storePath;
	}

	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}

	public List<BurnFile> getFileList() {
		return fileList;
	}

	public String getCurrent_user_iidd() {
		return current_user_iidd;
	}

	@Override
	public String executeFunction() throws Exception {
		event = enterService.getEnterEventByEnterCode(event_code);
		current_user_iidd = getCurUser().getUser_iidd();
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			storePath = PropertyUtil.getScanFileStorePath(event_code);
			File path = new File(storePath);
			fileList = new ArrayList<BurnFile>();
			if (path.isDirectory()) {
				File[] files = path.listFiles();
				for (File file : files) {
					fileList.add(new BurnFile(file.getName(), storePath + "/" + file.getName()));
				}
			}

			if (event.getFile_kind().equals("BOOK") && event.getScope().equals("DEPT")) {
				time_flag = "USETIME";
			}

			String job_code = enterService.getJobCodeByEventCode(event_code);
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				// process = basicService.getApproveProcessByKey(job.getDept_id(), job.getSeclv_code(), job.getJobType()
				// .getJobTypeCode());
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
			}
			return SUCCESS;
		}
	}
}
