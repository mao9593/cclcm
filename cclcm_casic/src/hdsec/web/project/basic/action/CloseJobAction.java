package hdsec.web.project.basic.action;

/**
 * 关闭任务
 * 
 * @author renmingfei
 * 
 */
public class CloseJobAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private String actionContext = "";
	private String jobType_code = "";
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code;
	}

	public String getActionContext() {
		return actionContext;
	}

	public void setActionContext(String actionContext) {
		this.actionContext = actionContext;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (job_code.isEmpty()) {
			throw new Exception("参数错误，没有任务流水号");
		} else {
			basicService.closeJob(job_code, jobType_code);
			insertCommonLog("取消任务：任务流水号[" + job_code + "]");
		}
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
