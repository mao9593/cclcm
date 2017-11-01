package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.computer.model.EntityInfoDevice;
import hdsec.web.project.computer.model.InfoDeviceEvent;

import java.util.List;

import org.springframework.util.StringUtils;

public class ViewInfoDeviceEventDetailAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private InfoDeviceEvent event = null;
	private EntityInfoDevice device = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private String type = "";

	public EntityInfoDevice getDevice() {
		return device;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public InfoDeviceEvent getEvent() {
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
		event = computerService.getInfoDeviceEventByEventCode(event_code);
		device = computerService.getInfoDeviceByBarcode(event.getDevice_barcode());
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String job_code = computerService.getInfoDeviceJobCodeByEventCode(event_code);
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
			}
			if (type.equals("CHANGE")) {
				return "okc";
			} else if (type.equals("DESTROY")) {
				return "okd";
			}
			return SUCCESS;
		}
	}
}