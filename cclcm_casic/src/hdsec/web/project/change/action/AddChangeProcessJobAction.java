package hdsec.web.project.change.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.change.model.EventChange;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加文件转换任务申请
 * 
 * @author lixiang
 * 
 */
public class AddChangeProcessJobAction extends ChangeBaseAction {

	private static final long serialVersionUID = 1L;
	private String entity_type = "";
	private String scope_dept_id = "";
	private Integer seclv_code = null;
	private String usage_code = "";
	private String comment = "";
	private String next_approver = "";

	private String change_user_iidd = "";// 选择的人的登陆名称

	private JobTypeEnum jobType = null;
	private String chkResult = "";
	private String _chk = "";
	private String barcode_codes = "";
	private Integer highest_seclv = null;
	private String submit = "N";
	private final ApproveProcess process = null;
	private final List<ApproverUser> userList = null;
	private List<EventChange> eventList;
	private String change_type = "";
	private String actionContext = "";
	private List<SecDept> deptList;
	private String dept_ids = "";

	public String getDept_ids() {
		return dept_ids;
	}

	public String getChange_user_iidd() {
		return change_user_iidd;
	}

	public void setChange_user_iidd(String change_user_iidd) {
		this.change_user_iidd = change_user_iidd;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getScope_dept_id() {
		return scope_dept_id;
	}

	public void setScope_dept_id(String scope_dept_id) {
		this.scope_dept_id = scope_dept_id;
	}

	public String get_chk() {
		return _chk;
	}

	public void set_chk(String _chk) {
		this._chk = _chk.replaceAll(" ", "");
	}

	public String getBarcode_codes() {
		return barcode_codes;
	}

	public void setBarcode_codes(String barcode_codes) {
		this.barcode_codes = barcode_codes;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<EventChange> getEventList() {
		return eventList;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getChange_type() {
		return change_type;
	}

	public void setChange_type(String change_type) {
		this.change_type = change_type;
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("CHANGE");
	}

	public int getHighest_seclv() {
		return highest_seclv;
	}

	public String getActionContext() {
		return actionContext;
	}

	public void setActionContext(String actionContext) {
		this.actionContext = actionContext;
	}

	public List<SecDept> getDeptList() {
		return deptList;
	}

	private EventChange generateChangeEvent(EntityPaper paper) {
		EventChange event = new EventChange();
		event.setSeclv_name(paper.getSeclv_name());
		event.setBarcode(paper.getPaper_barcode());
		event.setFile_name(paper.getFile_title());
		return event;
	}

	private EventChange generateChangeEvent(EntityCD cd) {
		EventChange event = new EventChange();
		event.setSeclv_name(cd.getSeclv_name());
		event.setBarcode(cd.getCd_barcode());
		event.setFile_name(cd.getFile_list());
		return event;
	}

	@Override
	public String executeFunction() throws Exception {

		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("user_iidd", getCurUser().getUser_iidd());
		// map.put("role_id", "6");
		// List<String> dept_id_list = basicMapper.getDeptIdListByUserRole(map);

		String webUrl = getModuleName().toLowerCase() + "/" + "viewdeptpaperledger.action";
		List<String> dept_id_list = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), webUrl);
		if (dept_id_list != null && dept_id_list.size() > 0) {
			dept_ids = dept_id_list.toString().replace("[", "").replace("]", "").replace(" ", "");
		}
		String web_url = getModuleName().toLowerCase() + "/" + "viewpersonalpaperledger.action";
		deptList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);

		if (submit.equalsIgnoreCase("Y")) {
			try {
				if (StringUtils.hasLength(barcode_codes)) {
					changeService.addProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
							jobType, next_approver, comment, StringUtil.stringArrayToList(barcode_codes.split(",")),
							usage_code, entity_type, scope_dept_id, change_type, change_user_iidd);
					insertCommonLog("提交" + jobType.getJobTypeName() + "申请" + barcode_codes);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加审批流程出现异常");
			}
			setChkResult("ok" + ":" + actionContext);
			return "ok";
		} else {
			if (StringUtils.hasLength(_chk)) {
				barcode_codes = _chk;
				List<Integer> seclvCodeList = new ArrayList<Integer>();
				String[] barcodes = _chk.split(",");
				List<EventChange> events = new ArrayList<EventChange>();
				if (entity_type.equalsIgnoreCase("paper")) {
					for (String barcode : barcodes) {
						EntityPaper paper = ledgerService.getPaperByBarcode(barcode);
						events.add(generateChangeEvent(paper));
						seclvCodeList.add(paper.getSeclv_code());
					}
				} else if (entity_type.equalsIgnoreCase("cd")) {
					for (String barcode : barcodes) {
						EntityCD cd = ledgerService.getCDByBarcode(barcode);
						events.add(generateChangeEvent(cd));
						seclvCodeList.add(cd.getSeclv_code());
					}
				}
				eventList = events;
				highest_seclv = basicService.getHighestSeclvByCodeList(seclvCodeList).getSeclv_rank();
			} else {
				throw new Exception("参数错误，没有文件编号");
			}
			return SUCCESS;
		}
	}
}