package hdsec.web.project.copy.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加复印任务申请
 * 
 * @author lixiang
 * 
 */
public class AddCopyProcessJobAction extends CopyBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_ids = "";
	private Integer seclv_code = null;
	private String submit = "N";
	private String comment = "";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private final SecLevel seclv = null;
	private final ApproveProcess process = null;
	private final List<ApproverUser> userList = null;
	private String chkResult = "";
	private List<CopyEvent> eventList;
	private String usage_code = "";
	private String project_code = "";
	private String copy_nums = "";
	private String _chk = "";
	private Integer highest_seclv = null;
	private String barcode_codes;
	private String cycle_type = "";
	private String output_dept_name = "";
	private String output_user_name = "";
	private String period = "L";
	private String copy_merge = "";
	private Integer copy_number = null;

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
		return userService.getCopySecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<CopyEvent> getEventList() {
		return eventList;
	}

	public void setCopy_nums(String copy_nums) {
		this.copy_nums = copy_nums;
	}

	public void setEvent_ids(String event_ids) {
		this.event_ids = event_ids.replaceAll(" ", "");
	}

	public String getEvent_ids() {
		return event_ids;
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

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public void setCycle_type(String cycle_type) {
		this.cycle_type = cycle_type;
	}

	public String getOutput_dept_name() {
		return output_dept_name;
	}

	public void setOutput_dept_name(String output_dept_name) {
		this.output_dept_name = output_dept_name;
	}

	public String getOutput_user_name() {
		return output_user_name;
	}

	public void setOutput_user_name(String output_user_name) {
		this.output_user_name = output_user_name;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getCopy_merge() {
		return copy_merge;
	}

	public void setCopy_merge(String copy_merge) {
		this.copy_merge = copy_merge;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public String getSubmit() {
		return submit;
	}

	public String getComment() {
		return comment;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public String getCopy_nums() {
		return copy_nums;
	}

	public String getCycle_type() {
		return cycle_type;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}

	public Integer getCopy_number() {
		return copy_number;
	}

	public void setCopy_number(Integer copy_number) {
		this.copy_number = copy_number;
	}

	public void setEventList(List<CopyEvent> eventList) {
		this.eventList = eventList;
	}

	public void setHighest_seclv(Integer highest_seclv) {
		this.highest_seclv = highest_seclv;
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("COPY");
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	private Map<String, String> getCopyNumList() {
		if (StringUtils.hasLength(copy_nums)) {
			Map<String, String> map = new HashMap<String, String>();
			for (String item : copy_nums.split(",")) {
				if (item.indexOf(":") != -1) {
					map.put(item.substring(0, item.indexOf(":")), item.substring(item.indexOf(":") + 1));
				}
			}
			return map;
		}
		return Collections.emptyMap();
	}

	@Override
	public String executeFunction() throws Exception {

		if (submit.equalsIgnoreCase("Y")) {
			try {
				if (StringUtils.hasLength(barcode_codes)) {
					if (copy_merge.equalsIgnoreCase("yes")) {
						System.out.print("copy_num" + copy_number);
						copyService.addMergeProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(),
								seclv_code, cycle_type, jobType, next_approver, output_dept_name, output_user_name,
								comment, StringUtil.stringArrayToList(barcode_codes.split(",")), usage_code,
								project_code, copy_number, period, copy_merge);
					} else {
						copyService.addProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
								cycle_type, jobType, next_approver, output_dept_name, output_user_name, comment,
								StringUtil.stringArrayToList(barcode_codes.split(",")), usage_code, project_code,
								getCopyNumList(), period, copy_merge);
					}

					insertCommonLog("提交" + jobType.getJobTypeName() + "申请" + barcode_codes);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加审批流程出现异常");
			}
			setChkResult("ok");
			return "ok";
		} else {
			if (StringUtils.hasLength(_chk)) {
				barcode_codes = _chk;
				List<Integer> seclvCodeList = new ArrayList<Integer>();
				String[] barcodes = _chk.split(",");
				List<CopyEvent> events = new ArrayList<CopyEvent>();
				for (String barcode : barcodes) {
					EntityPaper paper = ledgerService.getPaperByBarcode(barcode);
					events.add(generateCopyEvent(paper));
					seclvCodeList.add(paper.getSeclv_code());
				}
				eventList = events;
				highest_seclv = basicService.getHighestSeclvByCodeList(seclvCodeList).getSeclv_rank();
			} else {
				throw new Exception("参数错误，没有文件编号");
			}
			return SUCCESS;
		}
	}

	private CopyEvent generateCopyEvent(EntityPaper paper) {
		CopyEvent event = new CopyEvent();
		event.setSeclv_name(paper.getSeclv_name());
		event.setOriginalid(paper.getPaper_barcode());
		event.setFile_name(paper.getFile_title());
		event.setPage_num(paper.getPage_count());
		event.setCopy_merge(copy_merge);
		/*
		 * if (copy_merge != "no") { event.setCopy_num(copy_num); }
		 */
		return event;
	}

	public int getHighest_seclv() {
		return highest_seclv;
	}

}