package hdsec.web.project.transfer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.transfer.model.EventTransfer;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

public class AddPaperTransferEventAction extends TransferBaseAction {
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
	private final String jobType = JobTypeEnum.TRANSFER.getJobTypeCode();
	
	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			//save event
			transfer = getTransferEntityByID();
			transfer.setUsage_code(usage_code);
			transfer.setSumm(summ);
			transferService.savePaperEventTranfer(transfer);
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + System.currentTimeMillis();
			transfer = getTransferEntityByID();
			return SUCCESS;
		}
	}
	
	private EventTransfer getTransferEntityByID() {
		EntityPaper entity = ledgerService.getOnePaperLedgerById(id + "");
		EventTransfer transfer = null;
		if (null != entity) {
			transfer = new EventTransfer();
			transfer.setId(id + "");
			transfer.setEvent_code(event_code);
			transfer.setUser_iidd(getCurUser().getUser_iidd());
			transfer.setDept_id(getCurUser().getDept_id());
			transfer.setDept_name(userService.getDeptNameByDeptId(getCurUser().getDept_id()));
			transfer.setSeclv_code(entity.getSeclv_code());
			transfer.setSeclv_name(userService.getSecLevelByCode(entity.getSeclv_code()).getSeclv_name());
			transfer.setEntity_type(LedgerService.PAPER);
			transfer.setBarcode(entity.getPaper_barcode());
			transfer.setProject_name(entity.getProject_name());
			transfer.setApply_time(new Date());
			transfer.setAccept_user_iidd(accept_user_iidd);
			String accept_dept_id = transferService.getDeptIdByUserId(accept_user_iidd);
			transfer.setAccept_dept_id(accept_dept_id);
		}
		return transfer;
	}
	
	public String getEvent_code() {
		return event_code;
	}
	
	public String getEvent_codes() {
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
	
	public String getJobType() {
		return jobType;
	}
	
	public String getUser_iidd() {
		return getCurUser().getUser_iidd();
	}
	
	public String getDept_id() {
		return getCurUser().getDept_id();
	}
	
}
