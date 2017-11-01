package hdsec.web.project.basic.action;


/**
 * 取消任务
 * 
 * @author renmingfei
 * 
 */
public class CancelJobAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private String actionContext = "";
	private String jobType_code = "";
	private String type = "";
	private Integer print_status;
	private String event_code = "";

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

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

	public Integer getPrint_status() {
		return print_status;
	}

	public void setPrint_status(Integer print_status) {
		this.print_status = print_status;
	}

	@Override
	public String executeFunction() throws Exception {

		if (job_code.isEmpty()) {
			throw new Exception("参数错误，没有申请流水号");
		} else {
			int enterCancel;
			// 查询出未打印的event,并把相对应的job_code置为null
			if (jobType_code.equalsIgnoreCase("SPECIAL_PRINT")) {
				printService.cancelPrintEventByJobCode(job_code);
				// 查找job_code下是否有打印的(enterCancel为已打印数目)
				enterCancel = printService.getPrintEventEnterCancel(job_code);

			} else if (job_code.contains("LEADIN")) {
				enterService.cancelEnterEventByEventCode(event_code);
				enterCancel = enterService.getEnterEventEnterCancel(job_code);

			} else {
				printService.cancelPrintEventByJobCode(job_code);
				// 查找job_code下是否有打印的(enterCancel为已打印数目)
				enterCancel = printService.getPrintEventEnterCancel(job_code);
			}

			if (enterCancel == 0) {
				basicService.cancelJob(job_code, jobType_code);
			}
			insertCommonLog("撤销申请：申请流水号[" + job_code + "]");
		}
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
