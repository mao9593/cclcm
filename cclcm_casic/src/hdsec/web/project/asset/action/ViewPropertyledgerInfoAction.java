package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.asset.model.EntityProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 查看资产详情，有流程
 * 
 * @author gaoximin 2015-7-18
 */
public class ViewPropertyledgerInfoAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private String property_barcode = "";
	private String op = "";
	private EntityProperty property = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;

	public ApproveProcess getProcess() {
		return process;
	}

	public String getProperty_barcode() {
		return property_barcode;
	}

	public void setProperty_barcode(String property_barcode) {
		this.property_barcode = property_barcode;
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

	public EntityProperty getProperty() {
		return property;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("property_barcode", property_barcode);
		property = assetService.getPropertyByIDBarcode(map);
		// String seclv_name = " ";
		// if (null !=
		// userService.getSecLevelByCode(deviceEntity.getSeclv_code())) {
		// seclv_name =
		// userService.getSecLevelByCode(deviceEntity.getSeclv_code()).getSeclv_name();
		// }
		// deviceEntity.setSeclv_name(seclv_name);
		String job_code = property.getJob_code();
		if (StringUtils.hasLength(job_code)) {
			job = basicService.getProcessJobByCode(job_code);
			// process = basicService.getApproveProcessByKey(job.getDept_id(),
			// job.getSeclv_code(), job.getJobType()
			// .getJobTypeCode());
			process = basicPrcManage.getApproveProcessByInstanceId(job
					.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
		}
		return SUCCESS;
	}

}
