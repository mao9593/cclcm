package hdsec.web.project.arch.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.arch.model.ArchValue;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

public class AddArchProcessJobAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_codes = "";
	private Integer seclv_code = null;
	private String submit = "N";
	private String comment = "";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private List<ApproverUser> userList = null;
	private String chkResult = "";
	private ArchValue arch = null;
	private String seclv_name = "";
	private String barcodemedia = "";
	private String entity_type = "";
	private String filename = "";
	private Integer id = null;
	private String usage_code = "";
	private String arche_type = ""; // 借用档案类型 1 为电子档案，2为纸质文件

	public String getArche_type() {
		return arche_type;
	}

	public void setArche_type(String arche_type) {
		this.arche_type = arche_type;
	}

	public ArchValue getArch() {
		return arch;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
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

	public void setEvent_codes(String event_codes) {
		this.event_codes = event_codes;
	}

	public String getEvent_codes() {
		return event_codes;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = JobTypeEnum.valueOf(jobType);
	}

	public String getChkResult() {
		return chkResult;
	}

	public String getBarcodemedia() {
		return barcodemedia;
	}

	public void setBarcodemedia(String barcodemedia) {
		this.barcodemedia = barcodemedia;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public String getEntity_type_name() {
		String name = "档案";
		switch (this.entity_type) {
		case "PAPER":
			name = "纸质";
			break;
		case "CD":
			name = "光盘";
			break;
		case "DEVICE":
			name = "磁介质";
			break;
		}
		return name;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public List<SysUsage> getUsageList() {
		if (jobType == JobTypeEnum.BORROW) {
			return basicService.getSysUsageListByModule("BORROW");
		} else
			return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	@Override
	public String executeFunction() throws Exception {
		if (submit.equalsIgnoreCase("Y")) {
			try {
				jobType = changeJobType();
				archService.addBorrowProcessJob(getCurUser().getUser_iidd(),
						getCurUser().getDept_id(), seclv_code, jobType,
						next_approver, comment, event_codes, usage_code,
						entity_type, barcodemedia, filename, arche_type);
				insertCommonLog("提交" + jobType.getJobTypeName() + "审批申请"
						+ event_codes);
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加审批流程出现异常");
			}
			setChkResult("ok");
			return "ok";
		} else {
			if (jobType != JobTypeEnum.BRWARCH
					&& !StringUtils.hasLength(event_codes)) {
				throw new Exception("参数错误，没有作业流水号");
			} else {
				if (jobType == JobTypeEnum.BRWARCH) {// 借用
					event_codes = getCurUser().getUser_iidd()
							+ System.currentTimeMillis();
					arch = archService.getArchValueById(String.valueOf(id));
					barcodemedia = arch.getBarcode();
					filename = arch.getFile_title();
				}
			}
			return SUCCESS;
		}
	}

	private JobTypeEnum changeJobType() {

		for (JobTypeEnum job : JobTypeEnum.getAllJobType()) {
			if (job.getJobTypeCode().equals("BRWARCH")) {
				return job;
			}
		}
		return JobTypeEnum.BRWARCH;
	}
}
