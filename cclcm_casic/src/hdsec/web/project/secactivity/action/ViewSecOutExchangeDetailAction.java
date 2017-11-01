package hdsec.web.project.secactivity.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.secactivity.model.SecOutExchangeEvent;
import hdsec.web.project.secplace.model.EntityVisitor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 查看涉外交流作业详情
 * 
 * @author gj
 */
public class ViewSecOutExchangeDetailAction extends SecuActiBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private SecOutExchangeEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private List<BurnFile> burnFileList = null;
	private List<EntityVisitor> visitorList = null;

	public List<EntityVisitor> getVisitorList() {
		return visitorList;
	}

	public void setVisitorList(List<EntityVisitor> visitorList) {
		this.visitorList = visitorList;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public SecOutExchangeEvent getEvent() {
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

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	@Override
	public String executeFunction() throws Exception {
		event = secactivityservice.getSecOutExchangeByEventCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				String storePath = BMPropertyUtil.getSecActivityStrorePath();
				burnFileList = new ArrayList<BurnFile>();
				String file_path = "";
				for (int i = 0; i < filelist.length; i++) {
					file_path = storePath + "/" + event_code + "/" + filelist[i];
					burnFileList.add(new BurnFile(filelist[i], file_path));
				}
			}
			visitorList = secplaceService.getVisitorListByEventCode(event_code);

			String job_code = secactivityservice.getOutExchangeJobCodeByEventCode(event_code);
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
