package hdsec.web.project.transfer.action;

import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

public class SubmitUpdatePaperTransferEventAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Resource
	protected BasicService basicService;
	
	private String event_code;
	private int id;
	private EventTransfer transfer;
	private String entity_type;
	private String barcode;
	private String summ;
	private String accept_user_iidd;
	private String accept_user_name;
	private String accept_dept_id;
	private String accept_dept_name;
	private Date apply_time;
	private int transfer_status;
	private String his_job_code;
	private String usage_code;
	
	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("event_code", event_code);
		map.put("summ", summ);
		map.put("usage_code", usage_code);
		map.put("accept_user_iidd", accept_user_iidd);
		transferService.updateEventTransfer(map);
		return "ok";
	}
	
	public String getEvent_code() {
		return event_code;
	}
	
	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public EventTransfer getTransfer() {
		return transfer;
	}
	
	public void setTransfer(EventTransfer transfer) {
		this.transfer = transfer;
	}
	
	public BasicService getBasicService() {
		return basicService;
	}
	
	public void setBasicService(BasicService basicService) {
		this.basicService = basicService;
	}
	
	public String getEntity_type() {
		return entity_type;
	}
	
	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public String getSumm() {
		return summ;
	}
	
	public void setSumm(String summ) {
		this.summ = summ;
	}
	
	public String getAccept_user_iidd() {
		return accept_user_iidd;
	}
	
	public void setAccept_user_iidd(String accept_user_iidd) {
		this.accept_user_iidd = accept_user_iidd;
	}
	
	public String getAccept_dept_id() {
		return accept_dept_id;
	}
	
	public void setAccept_dept_id(String accept_dept_id) {
		this.accept_dept_id = accept_dept_id;
	}
	
	public Date getApply_time() {
		return apply_time;
	}
	
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	
	public int getTransfer_status() {
		return transfer_status;
	}
	
	public void setTransfer_status(int transfer_status) {
		this.transfer_status = transfer_status;
	}
	
	public String getHis_job_code() {
		return his_job_code;
	}
	
	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}
	
	public String getUsage_code() {
		return usage_code;
	}
	
	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}
	
	public String getAccept_user_name() {
		return accept_user_name;
	}
	
	public void setAccept_user_name(String accept_user_name) {
		this.accept_user_name = accept_user_name;
	}
	
	public String getAccept_dept_name() {
		return accept_dept_name;
	}
	
	public void setAccept_dept_name(String accept_dept_name) {
		this.accept_dept_name = accept_dept_name;
	}
	
}
