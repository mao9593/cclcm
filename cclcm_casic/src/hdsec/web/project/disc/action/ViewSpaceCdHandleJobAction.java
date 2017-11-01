package hdsec.web.project.disc.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

/**
 * 查看已审批空白盘任务列表
 * 
 * @author zp
 * 
 */
public class ViewSpaceCdHandleJobAction extends DiscBaseAction {

	private static final long serialVersionUID = 1L;
	private String user_name = "";
	private Integer seclv_code = null;
	private List<ProcessJob> jobList = null;
	private String entity_type = "";

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

	public String getEntity_type() {
		return entity_type;
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public void setJobList(List<ProcessJob> jobList) {
		this.jobList = jobList;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		jobList = basicService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SPACECD_BORROW,
				user_name, seclv_code);
		return SUCCESS;
	}

}
