package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * BM审批记录
 * 
 * @author gaoximin 2015-10-14
 */
public class ViewAprvJobAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;
	private String type = "";
	private String module = "";// 空默认为人员模块，其他根据参数判定模块（保密处管理、其他保密管理）

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name.trim();
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public String executeFunction() throws Exception {
		List<ProcessJob> tempList = null;
		jobList = new ArrayList<ProcessJob>();
		if (module.equals("")) {
			// 涉密人员
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.USER_INFO,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.USERSECLV_ADD,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.USERSECLV_CHANGE,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.RESIGN, user_name,
					seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SECUSER_ABROAD,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SECUSER_ENTRUST,
					user_name, seclv_code);
			jobList.addAll(tempList);
		} else if (module.equals("secmanageBMC")) {
			// 保密处管理
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SEC_CHECK,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PUNISH_DEPT,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PUNISH_SECCHECK,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PUNISH_RECTIFY,
					user_name, seclv_code);
			jobList.addAll(tempList);
		} else if (module.equals("secmanage")) {
			// 其他保密管理
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FIELDIN, user_name,
					seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FILEOUTMAKE,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.USERSEC_ACTIVITY,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.ENTER_SECPLACE,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.OUT_EXCHANGE,
					user_name, seclv_code);
			jobList.addAll(tempList);
		} else if (module.equals("publicity")) {
			// 宣传报道及提供资料管理
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPORT,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPORT2,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_REPORT3,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_DEPTREPORT,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_INTRAPUBL,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EVENT_INTERPUBL,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.MATERIAL,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.INTER_EMAIL,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EXHIBITION,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PAPER_RESEARCH,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PAPER_OTHERS,
					user_name, seclv_code);
			jobList.addAll(tempList);
			tempList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PAPERPATENT,
					user_name, seclv_code);
			jobList.addAll(tempList);
		}

		return SUCCESS;
	}
}
