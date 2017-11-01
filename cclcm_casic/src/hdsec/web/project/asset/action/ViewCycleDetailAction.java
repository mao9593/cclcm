package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.asset.model.EntityProperty;
import hdsec.web.project.basic.model.RejectRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看资产载体全生命周期详细信息
 * 
 * @author gaoximin 2015-7-15
 */
public class ViewCycleDetailAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private String barcode = "";
	private EntityProperty property = null;
	private List<CycleItem> itemList = null;

	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<RejectRecord> rejectRecordList = null;

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public List<CycleItem> getItemList() {
		return itemList;
	}

	public EntityProperty getProperty() {
		return property;
	}

	public ProcessJob getJob() {
		return job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public List<RejectRecord> getRejectRecordList() {
		return rejectRecordList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("property_barcode", barcode);
		property = assetService.getPropertyByIDBarcode(map);
		itemList = assetService.getCycleItemListByBarcode(barcode);
		// 流程展示
		job = basicService.getProcessJobByCode(job_code);
		if (job != null) {
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			process = basicPrcManage.getApproveProcessByInstanceId(job
					.getInstance_id());
		}
		return SUCCESS;
	}
}
