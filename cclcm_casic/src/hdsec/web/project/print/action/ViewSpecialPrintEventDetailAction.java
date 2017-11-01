package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.print.model.OaPrintEvent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 查看特殊打印作业详细
 * 
 * @author guojiao
 * 
 */
public class ViewSpecialPrintEventDetailAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	// private String id = "";
	private String event_code = "";
	private String op = "";
	private OaPrintEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private List<BurnFile> burnFileList = null;

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
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

	public OaPrintEvent getEvent() {
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

	@Override
	public String executeFunction() throws Exception {
		event = printService.getSpecialPrintEventByEventCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业不存在或者已经被删除");
		} else {
			String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				String storePath = PropertyUtil.getOaFileStorePath();
				burnFileList = new ArrayList<BurnFile>();
				String file_path = "";
				for (int i = 0; i < filelist.length; i++) {
					file_path = storePath + "/" + event.getEvent_code() + "/" + MD5.getStringMD5(filelist[i]);
					burnFileList.add(new BurnFile(filelist[i], file_path));
				}
			}
			if (StringUtils.hasLength(event.getEvent_code())) {
				String job_code = printService.getJobCodeByEventCode(event.getEvent_code());
				if (StringUtils.hasLength(job_code)) {
					job = basicService.getProcessJobByCode(job_code);
					process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
					ProcessRecord record = new ProcessRecord();
					record.setJob_code(job_code);
					recordList = activitiService.getProcessRecordList(record);
				}
			}
			return SUCCESS;
		}
	}
}
