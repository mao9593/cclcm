package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.secmanage.model.PaperPatentEvent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 查看论文申请专利发表任务详情
 * 
 * @author gaoximin 2015-9-17
 */
public class ViewPaperPatentDetailAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private PaperPatentEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private List<BurnFile> burnFileList = null;

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public PaperPatentEvent getEvent() {
		return event;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public ProcessJob getJob() {
		return job;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		event = secManageService.getPaperPatentEventByEventCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				String storePath = BMPropertyUtil.getSecManageStrorePath();
				burnFileList = new ArrayList<BurnFile>();
				String file_path = "";
				for (int i = 0; i < filelist.length; i++) {
					file_path = storePath + "/" + event_code + "/" + filelist[i];
					burnFileList.add(new BurnFile(filelist[i], file_path));
				}
			}
			String job_code = secManageService.getExhibitionJobCodeByEventCode(event_code);
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