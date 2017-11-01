package hdsec.web.project.transfer.action;

import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

public class ViewCDTransferEventDetailAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Resource
	protected BasicService basicService;
	
	private String event_code;
	private int id;
	private EventTransfer transfer;
	private String barcode;
	private String summ;
	private String accept_user_name;
	private String accept_dept_name;
	private Date apply_time;
	private String his_job_code;
	private String usage_code;
	private String seclv_name;
	private String usage_name;
	private String project_name;
	
	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}
	
	@Override
	public String executeFunction() throws Exception {
		transfer = transferService.getOneTransferEventByCode(event_code);
		project_name = transfer.getProject_name();
		seclv_name = transfer.getSeclv_name();
		accept_user_name = userService.getUserNameByUserId(transfer.getAccept_user_iidd());
		//		accept_dept_name = userService.getDeptNameByDeptId(transfer.getAccept_dept_id());
		apply_time = transfer.getApply_time();
		return SUCCESS;
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
	
	public String getApply_time() {
		return sdf.format(apply_time);
	}
	
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
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
	
	public String getSeclv_name() {
		return seclv_name;
	}
	
	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}
	
	public String getUsage_name() {
		return usage_name;
	}
	
	public void setUsage_name(String usage_name) {
		this.usage_name = usage_name;
	}
	
	public String getProject_name() {
		return project_name;
	}
	
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
}
