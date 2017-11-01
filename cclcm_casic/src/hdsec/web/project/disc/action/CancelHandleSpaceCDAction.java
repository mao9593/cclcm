package hdsec.web.project.disc.action;

/**
 * 取消空白盘载体处理申请
 * 
 * @author zp
 * 
 */
public class CancelHandleSpaceCDAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String id = "";
	private String type = "";
	private String job_code = "";
	private String actionContext = "";
	private String jobType_code = "";
	private Integer print_status;

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getActionContext() {
		return actionContext;
	}

	public void setActionContext(String actionContext) {
		this.actionContext = actionContext;
	}

	public String getJobType_code() {
		return jobType_code;
	}

	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code;
	}

	public Integer getPrint_status() {
		return print_status;
	}

	public void setPrint_status(Integer print_status) {
		this.print_status = print_status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {

		if (job_code.isEmpty()) {
			throw new Exception("参数错误，没有申请流水号");
		} else {
			// 查询出未打印的event,并把相对应的job_code置为null
			discService.cancelSpaceEventByJobCode(job_code);

			// 查找job_code下是否有打印的(enterCancel为已打印数目)
			int enterCancel = discService.getSpaceCDEventEnterCancel(job_code);
			if (enterCancel == 0) {
				basicService.cancelJob(job_code, jobType_code);
			}
			insertCommonLog("取消空白盘载体处理申请[" + job_code + "]");
		}

		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
