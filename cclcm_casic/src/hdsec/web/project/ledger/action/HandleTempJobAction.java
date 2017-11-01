package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 其他临时申请表处理
 * 
 * @author guojiao
 * 
 */
public class HandleTempJobAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String _chk = "";
	private String addjob = "N";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private Integer seclv_code = null;
	private List<EntityPaper> paperList = null;
	private List<EntityCD> cdList = null;
	// private String reason = "";// 录入文件错误发起流程填写原因
	private String scope_dept_id = "";// 载体归属部门ID
	private String scope_dept_name = "";// 载体归属部门名称
	private String paper_barcodes = "";
	private String entity_type = "";
	private String usage_code = "";
	private String project_code = "";
	private String summ = "";
	private Integer highest_seclv = 10000;

	public List<EntityCD> getCdList() {
		return cdList;
	}

	public Integer getHighest_seclv() {
		return highest_seclv;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getPaper_barcodes() {
		return paper_barcodes;
	}

	public void setPaper_barcodes(String paper_barcodes) {
		this.paper_barcodes = paper_barcodes;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public void setScope_dept_id(String scope_dept_id) {
		this.scope_dept_id = scope_dept_id;
	}

	public void setScope_dept_name(String scope_dept_name) {
		this.scope_dept_name = scope_dept_name;
	}

	public List<EntityPaper> getPaperList() {
		return paperList;
	}

	public void set_chk(String _chk) {
		this._chk = _chk.replaceAll(" ", "");
	}

	public String get_chk() {
		return _chk;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setAddjob(String addjob) {
		this.addjob = addjob;
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

	public List<SecLevel> getSeclvList() {
		return userService.getImportSecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("PRINT");
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	@Override
	public String executeFunction() throws Exception {
		if (addjob.equalsIgnoreCase("Y")) {
			try {
				ledgerService.addTempEvent(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
						paper_barcodes, jobType, entity_type, usage_code, project_code, summ, next_approver,
						scope_dept_id, scope_dept_name);

				insertCommonLog("提交纸质载体" + jobType.getJobTypeName() + "审批申请：条码号[" + _chk + "].");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (entity_type.equals("cd")) {
				return "cd";
			} else {
				return "ok";
			}
		} else {

			if (StringUtils.hasLength(_chk)) {
				if (entity_type.equals("paper")) {
					paperList = basicService.getPaperListByBarcodes(_chk);
					for (EntityPaper item : paperList) {
						if (jobType.getJobTypeCode().contains("PAPER_DEL")
								|| jobType.getJobTypeCode().contains("PAPER_MODIFY")) {
							if (!item.getCreate_type().contains("LEADIN")) {
								throw new Exception("文件修改/删除流程申请仅限于【录入】产生的台账，请重新选择台账列表");
							}
						}
						SecLevel seclevel = userService.getSecLevelByCode(item.getSeclv_code());
						if (highest_seclv > seclevel.getSeclv_rank()) {
							highest_seclv = seclevel.getSeclv_rank();
						}
					}
				} else {
					cdList = basicService.getCDListByBarcodes(_chk);
					for (EntityCD item : cdList) {
						SecLevel seclevel = userService.getSecLevelByCode(item.getSeclv_code());
						if (highest_seclv > seclevel.getSeclv_rank()) {
							highest_seclv = seclevel.getSeclv_rank();
						}
					}
				}

			} else {
				throw new Exception("参数错误，没有载体条码号");
			}
			return SUCCESS;
		}
	}
}
