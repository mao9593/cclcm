package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

/**
 * 资产入库已审批列表，台账
 * 
 * @author gaoximin 2015-7-18
 */
public class ViewInAprvJobAction extends AssetBaseAction {

	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;
	private String jobType_code = null;

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

	public String getJobType_code() {
		return jobType_code;
	}

	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		jobList = assetService.getHandledInJobByUserId(getCurUser()
				.getUser_iidd(), "PROPERTY", user_name, seclv_code,
				jobType_code);
		return SUCCESS;
	}

}
