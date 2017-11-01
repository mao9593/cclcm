package hdsec.web.project.device.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.device.model.EntityDevice;

import java.util.List;

import org.springframework.util.StringUtils;

public class ViewDeviceledgerInfoAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private String device_code = "";
	private String op = "";
	private EntityDevice deviceEntity = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private String supervise_user_name = "";

	public String getSupervise_user_name() {
		return supervise_user_name;
	}

	public void setSupervise_user_name(String supervise_user_name) {
		this.supervise_user_name = supervise_user_name;
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

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public EntityDevice getDeviceEntity() {
		return deviceEntity;
	}

	public void setDeviceEntity(EntityDevice deviceEntity) {
		this.deviceEntity = deviceEntity;
	}

	@Override
	public String executeFunction() throws Exception {
		deviceEntity = deviceService.getDeviceByCode(device_code);
		String seclv_name = " ";
		if (null != userService.getSecLevelByCode(deviceEntity.getSeclv_code())) {
			seclv_name = userService.getSecLevelByCode(deviceEntity.getSeclv_code()).getSeclv_name();
		}
		deviceEntity.setSeclv_name(seclv_name);

		if ((deviceEntity.getJob_code() != null) && (!deviceEntity.getJob_code().equals(""))) {
			if (deviceEntity.getJob_code().contains("DESTROY_DEVICE_BYSELF"))
				supervise_user_name = userService.getUserNameByUserId(deviceEntity.getSupervise_user_iidd());
		}

		String job_code = deviceEntity.getJob_code();
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

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}
}
