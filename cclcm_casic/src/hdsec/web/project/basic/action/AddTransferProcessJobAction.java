package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.transfer.model.EventTransfer;
import hdsec.web.project.transfer.service.TransferService;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

/**
 * 添加流转流程任务申请
 * 
 * @author yy
 * 
 */
public class AddTransferProcessJobAction extends BasicBaseAction {

	@Resource
	protected TransferService transferService;

	private static final long serialVersionUID = 1L;
	private Integer seclv_code = null;
	private String output_dept_name = "";
	private String output_user_name = "";
	private String comment = "";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private SecLevel seclv = null;
	private ApproveProcess process = null;
	private List<ApproverUser> userList = null;
	private String chkResult = "";
	private String actionContext = "";
	private String one_cycle_type;
	private String event_code;
	private String event_ids;
	private EventTransfer transfer;
	private String entity_type;
	private String barcode;
	private String summ;
	private String accept_user_iidd;
	private String accept_user_name;
	private String accept_dept_id;
	private String accept_dept_name;
	private Date apply_time;
	private String his_job_code;
	private String usage_code = "";
	private String type;
	private String project_code = "";
	private int job_seclv_code;
	private List<Integer> seclvCodeList = new ArrayList<>();

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("TRANSFER");
	}

	public void setOutput_dept_name(String output_dept_name) {
		this.output_dept_name = output_dept_name;
	}

	public void setOutput_user_name(String output_user_name) {
		this.output_user_name = output_user_name;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getActionContext() {
		return actionContext;
	}

	public void setActionContext(String actionContext) {
		this.actionContext = actionContext;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = JobTypeEnum.valueOf(jobType);
	}

	public SecLevel getSeclv() {
		return seclv;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	private EventTransfer getTransferEntityByPaperID(String id) {
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
			if ("paper".equals(type)) {
				transfer.setEntity_type(LedgerService.PAPER);
			} else if ("disk".equals(type)) {
				transfer.setEntity_type(LedgerService.DISK);
			}
			transfer.setBarcode(entity.getPaper_barcode());
			transfer.setProject_name(entity.getProject_name());
			transfer.setApply_time(new Date());
			transfer.setAccept_user_iidd(accept_user_iidd);
			String accept_dept_id = transferService.getDeptIdByUserId(accept_user_iidd);
			transfer.setAccept_dept_id(accept_dept_id);
		}
		return transfer;
	}

	private EventTransfer getTransferEntityByCDID(String id) {
		EntityCD entity = ledgerService.getOneCDLedgerById(Integer.parseInt(id));
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
			transfer.setEntity_type(LedgerService.DISK);
			transfer.setBarcode(entity.getCd_barcode());
			transfer.setProject_name(entity.getProject_name());
			transfer.setApply_time(new Date());
			transfer.setAccept_user_iidd(accept_user_iidd);
			String accept_dept_id = transferService.getDeptIdByUserId(accept_user_iidd);
			transfer.setAccept_dept_id(accept_dept_id);
		}
		return transfer;
	}

	@Override
	public String executeFunction() throws Exception {

		if (StringUtils.hasLength(event_code)) {
			String[] ids = event_ids.split(":");
			StringBuffer event_codes = new StringBuffer();
			for (String id : ids) {
				event_codes.append(event_code).append(id).append(":");
				if (null != id || !("").equals(id)) {
					if ("paper".equals(type)) {
						transfer = getTransferEntityByPaperID(id);
						transfer.setUsage_code(usage_code);
						transfer.setSumm(summ);
						transfer.setEvent_code(event_code + id);
						transferService.savePaperEventTranfer(transfer);
					} else if ("disk".equals(type)) {
						transfer = getTransferEntityByCDID(id);
						transfer.setUsage_code(usage_code);
						transfer.setSumm(summ);
						transfer.setEvent_code(event_code + id);
						transferService.saveCDEventTranfer(transfer);
					}
				}
			}
			// transferService.updateEventTranfer(transfer);
			try {
				jobType = JobTypeEnum.TRANSFER;
				basicService.addProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), job_seclv_code,
						jobType, next_approver, output_dept_name, output_user_name, comment, event_codes.toString(),
						usage_code, project_code);
				insertCommonLog("提交" + jobType.getJobTypeName() + "审批申请" + event_codes.toString());
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加审批流程出现异常");
			}
			setChkResult("ok" + ":" + actionContext);
			return "ok";

		} else {
			if (StringUtils.hasLength(event_ids)) {
				String[] ids = event_ids.split(":");
				seclv_code = 10000;
				for (String id : ids) {
					if ("paper".equals(type)) {
						EntityPaper paper = ledgerService.getPaperById(id);
						seclvCodeList.add(paper.getSeclv_code());
						if (seclv_code > paper.getSeclv_code()) {
							seclv_code = paper.getSeclv_code();
						}
					} else if ("disk".equals(type)) {
						EntityCD cd = ledgerService.getOneCDLedgerById(Integer.parseInt(id));
						seclvCodeList.add(cd.getSeclv_code());
						if (seclv_code > cd.getSeclv_code()) {
							seclv_code = cd.getSeclv_code();
						}
					}
				}
			}
			event_code = getCurUser().getUser_iidd() + "-TRANSFER-" + System.currentTimeMillis();
			seclv = userService.getSecLevelByCode(seclv_code);
			return SUCCESS;
		}
	}

	public List<EntityPaper> getPapers() {
		List<EntityPaper> papers = new ArrayList<EntityPaper>();
		if ("paper".equals(type)) {
			if (StringUtils.hasLength(event_code)) {
				String[] ids = event_ids.split(":");
				for (String id : ids) {
					if (null != id || !("").equals(id)) {
						papers.add(ledgerService.getOnePaperLedgerById(id));
					}
				}
			}
		}
		return papers;
	}

	public List<EntityCD> getCds() {
		List<EntityCD> cds = new ArrayList<EntityCD>();
		if ("disk".equals(type)) {
			if (StringUtils.hasLength(event_code)) {
				String[] ids = event_ids.split(":");
				for (String id : ids) {
					if (null != id || !("").equals(id)) {
						cds.add(ledgerService.getOneCDLedgerById(Integer.parseInt(id)));
					}
				}
			}
		}
		return cds;
	}

	public List<SecLevel> getSeclvList() {
		SecLevel level = basicService.getHighestSeclvByCodeList(seclvCodeList);
		return basicService.getHigherSeclvList(level);
		// return userService.getSecLevel();
	}

	public String getOne_cycle_type() {
		return one_cycle_type;
	}

	public void setOne_cycle_type(String one_cycle_type) {
		this.one_cycle_type = one_cycle_type;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
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

	public String getAccept_user_iidd() {
		return accept_user_iidd;
	}

	public void setAccept_user_iidd(String accept_user_iidd) {
		this.accept_user_iidd = accept_user_iidd;
	}

	public String getAccept_user_name() {
		return accept_user_name;
	}

	public void setAccept_user_name(String accept_user_name) {
		this.accept_user_name = accept_user_name;
	}

	public String getAccept_dept_id() {
		return accept_dept_id;
	}

	public void setAccept_dept_id(String accept_dept_id) {
		this.accept_dept_id = accept_dept_id;
	}

	public String getAccept_dept_name() {
		return accept_dept_name;
	}

	public void setAccept_dept_name(String accept_dept_name) {
		this.accept_dept_name = accept_dept_name;
	}

	public Date getApply_time() {
		return apply_time;
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

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public String getEvent_ids() {
		return event_ids;
	}

	public void setEvent_ids(String event_ids) {
		this.event_ids = event_ids;
	}

	public void setJob_seclv_code(int job_seclv_code) {
		this.job_seclv_code = job_seclv_code;
	}

}
