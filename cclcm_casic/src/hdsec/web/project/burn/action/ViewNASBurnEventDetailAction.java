package hdsec.web.project.burn.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.MD5;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 查看刻录作业详细
 * 
 * @author YY
 * 
 */
public class ViewNASBurnEventDetailAction extends BurnBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private BurnEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private List<BurnFile> burnFileList = null;
	private boolean is_cd_audit = false;

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

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	public BurnEvent getEvent() {
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

	public boolean isIs_cd_audit() {
		return is_cd_audit;
	}

	public void setIs_cd_audit(boolean is_cd_audit) {
		this.is_cd_audit = is_cd_audit;
	}

	@Override
	public String executeFunction() throws Exception {
		event = burnService.getBurnEventByBurnCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
			String[] fileseclevel = event.getFile_seclevel().split(CCLCMConstants.DEVIDE_SYMBOL);
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				String storePath = PropertyUtil.getBurnFileStorePath();
				burnFileList = new ArrayList<BurnFile>();
				Integer seclv_code;
				String seclv_name = "";
				String file_path = "";
				for (int i = 0; i < filelist.length; i++) {
					seclv_code = Integer.parseInt(fileseclevel[i].trim());
					seclv_name = userService.getSecLevelByCode(seclv_code).getSeclv_name();
					file_path = storePath + "/" + event_code + "/" + MD5.getStringMD5(filelist[i]);
					burnFileList.add(new BurnFile(filelist[i], seclv_code, seclv_name, file_path));
				}
			}
			String job_code = burnService.getJobCodeByEventCode(event_code);
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
