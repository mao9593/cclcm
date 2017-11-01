package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.asset.model.StorageEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 查看资产入库详情
 * 
 * @author gaoximin 2015-7-29
 */
public class ViewStorageEventDetailAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private StorageEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private String updatFlag = "";
	private String property_no = "";
	private String voucher_no = "";
	private String approveFlag = "";

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

	public StorageEvent getEvent() {
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

	public String getUpdatFlag() {
		return updatFlag;
	}

	public void setUpdatFlag(String updatFlag) {
		this.updatFlag = updatFlag;
	}

	public String getProperty_no() {
		return property_no;
	}

	public void setProperty_no(String property_no) {
		this.property_no = property_no;
	}

	public String getVoucher_no() {
		return voucher_no;
	}

	public void setVoucher_no(String voucher_no) {
		this.voucher_no = voucher_no;
	}

	public String getApproveFlag() {
		return approveFlag;
	}

	public void setApproveFlag(String approveFlag) {
		this.approveFlag = approveFlag;
	}

	@Override
	public String executeFunction() throws Exception {
		if (approveFlag.equals("Y")) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!property_no.equals("")) {
				map.put("event_code", event_code);
				map.put("property_no", property_no);
				assetService.updatePropertyNoByCode(map);
			}
			if (!voucher_no.equals("")) {
				map.put("event_code", event_code);
				map.put("voucher_no", voucher_no);
				assetService.updateVoucherNoByCode(map);
			}
		}
		event = assetService.getStorageEventByCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String job_code = assetService
					.getStorageJobCodeByEventCode(event_code);
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job
						.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
			}
			return SUCCESS;

		}
	}
}
