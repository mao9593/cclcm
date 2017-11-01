package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EventModify;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class HandleModifyJobAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;

	private String user_iidd;
	private String dept_id;
	private String event_code;
	private String entity_type;// 载体类型(CD,PAPER)
	private String paper_barcode;
	private String barcode; // 载体条码
	private int modify_status = 0;// 变更状态（1已完成，0未完成）
	private int pre_seclv;// 原密级
	private int trg_seclv;// 目标密码
	private Date finish_time = null;// 完成时间
	private String file_name;//
	private String summ;
	private JobTypeEnum jobType = null;
	private String job_code = "";// 任务号
	private SecLevel seclv = null;
	private ApproveProcess process = null;
	private List<ApproverUser> userList = null;
	private EventModify event;
	private Date apply_time;
	private int job_seclv_code;
	private String project_code = "";
	private String event_ids = "";
	private String flag = "N";
	private Integer seclv_code;
	private String seclv_name = "";// 密级
	private String usage_code = "";
	private String file_titles = "";
	private String log_file_name = "";
	private String next_approver;
	private List<EntityPaper> entityList = null;
	private List<EntityCD> entityCDList = null;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<EntityPaper> getEntityList() {
		return entityList;
	}

	public List<EntityCD> getEntityCDList() {
		return entityCDList;
	}

	public void setEntityCDList(List<EntityCD> entityCDList) {
		this.entityCDList = entityCDList;
	}

	public void setEntityList(List<EntityPaper> entityList) {
		this.entityList = entityList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
		// return userService.getSecLevel();
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

	public int getModify_status() {
		return modify_status;
	}

	public void setModify_status(int modify_status) {
		this.modify_status = modify_status;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public SecLevel getSeclv() {
		return seclv;
	}

	public void setSeclv(SecLevel seclv) {
		this.seclv = seclv;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public void setProcess(ApproveProcess process) {
		this.process = process;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ApproverUser> userList) {
		this.userList = userList;
	}

	public EventModify getEvent() {
		return event;
	}

	public void setEvent(EventModify event) {
		this.event = event;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}

	public int getJob_seclv_code() {
		return job_seclv_code;
	}

	public void setJob_seclv_code(int job_seclv_code) {
		this.job_seclv_code = job_seclv_code;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getEvent_ids() {
		return event_ids;
	}

	public void setEvent_ids(String event_ids) {
		this.event_ids = event_ids;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public int getPre_seclv() {
		return pre_seclv;
	}

	public void setPre_seclv(int pre_seclv) {
		this.pre_seclv = pre_seclv;
	}

	public int getTrg_seclv() {
		return trg_seclv;
	}

	public void setTrg_seclv(int trg_seclv) {
		this.trg_seclv = trg_seclv;
	}

	public Date getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getFile_titles() {
		return file_titles;
	}

	public void setFile_titles(String file_titles) {
		this.file_titles = file_titles;
	}

	public String getLog_file_name() {
		return log_file_name;
	}

	public void setLog_file_name(String log_file_name) {
		this.log_file_name = log_file_name;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	private Map<String, String> getFileTitleList() {
		if (StringUtils.hasLength(file_titles)) {
			Map<String, String> map = new HashMap<String, String>();
			for (String item : file_titles.split(",")) {
				if (item.indexOf(":") != -1) {
					map.put(item.substring(0, item.indexOf(":")), item.substring(item.indexOf(":") + 1));
					log_file_name += item.substring(item.indexOf(":") + 1) + ",";
				}
			}
			return map;
		}
		return Collections.emptyMap();
	}

	@Override
	public String executeFunction() throws Exception {
		if (flag.equalsIgnoreCase("Y")) {
			ledgerService.addProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code, event_ids,
					entity_type, modify_status, trg_seclv, usage_code, project_code, summ, getFileTitleList(),
					next_approver);
			insertCommonLog("提交" + JobTypeEnum.MODIFY_SECLV.getJobTypeName() + "申请:" + log_file_name);
			return "ok";
		} else {
			if (StringUtils.hasLength(event_code)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("event_ids", event_ids.split(":"));
				if (entity_type.equals("Paper")) {
					entityList = ledgerService.getAllPaperLedgerList(map);
				} else {
					entityCDList = ledgerService.getAllCDLedgerList(map);
				}
			}
			return SUCCESS;
		}
	}
}
