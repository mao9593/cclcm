package hdsec.web.project.client.action;

import hdsec.web.project.client.service.ClientService;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 三部代办消息集成时写入登录session,并跳转到审批页面
 * 
 * @author lixiang
 */
public class PendingWorkLoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private ClientService clientService;
	private String errorMsg = "";
	private String job_code = "";
	private String actionContext = "";
	private String user_iidd = "";

	public void setUser_iidd(String user_iidd) {
		logger.info("user_iidd from url is:" + user_iidd);
		this.user_iidd = user_iidd;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setJob_code(String job_code) {
		logger.info("job_code from url is:" + job_code);
		this.job_code = job_code;
	}

	public String getActionContext() {
		return actionContext;
	}

	public void setActionContext(String actionContext) {
		this.actionContext = actionContext;
	}

	@Override
	public String execute() {
		String oper_type = "";
		List<String> operTypeList = clientService.getOperTypeByJobCode(job_code);
		if (operTypeList != null && operTypeList.size() > 0) {
			oper_type = operTypeList.get(0);
			logger.info("oper_type is :" + oper_type);
			switch (oper_type) {
			case "PRINT":
				actionContext = "print/approveprintjob.action?job_code=" + job_code;
				break;
			case "FILE_PAPER":
				actionContext = "basic/handlejob.action?type=PAPER&job_code=" + job_code;
				break;
			case "SEND_PAPER":
				actionContext = "basic/handlejob.action?type=PAPER&job_code=" + job_code;
				break;
			case "DESTROY_PAPER":
				actionContext = "basic/handlejob.action?type=PAPER&job_code=" + job_code;
				break;
			case "DELAY_PAPER":
				actionContext = "basic/handlejob.action?type=PAPER&job_code=" + job_code;
				break;
			case "BURN":
				actionContext = "basic/approvejob.action?job_code=" + job_code;
				break;
			case "FILE_CD":
				actionContext = "basic/handlejob.action?type=CD&job_code=" + job_code;
				break;
			case "SEND_CD":
				actionContext = "basic/handlejob.action?type=CD&job_code=" + job_code;
				break;
			case "DESTROY_CD":
				actionContext = "basic/handlejob.action?type=CD&job_code=" + job_code;
				break;
			case "DELAY_CD":
				actionContext = "basic/handlejob.action?type=CD&job_code=" + job_code;
				break;
			case "COPY":
				actionContext = "copy/approvecopyjob.action?job_code=" + job_code;
				break;
			case "OUTCOPY":
				actionContext = "copy/approvecopyjob.action?job_code=" + job_code;
				break;
			case "LEADIN":
				actionContext = "enter/approveenterjob.action?job_code=" + job_code;
				break;
			case "TRANSFER":
				actionContext = "basic/approvejob.action?job_code=" + job_code;
				break;
			case "DEVICE":
				actionContext = "device/approvedevicejob.action?job_code=" + job_code;
				break;
			case "DESTROY_DEVICE":
				actionContext = "basic/handlejob.action?type=DEVICE&job_code=" + job_code;
				break;
			case "BORROW":
				actionContext = "basic/approvejob.action?job_code=" + job_code;
				break;
			case "CHANGE":
				actionContext = "change/approvechangejob.action?job_code=" + job_code;
				break;
			}
			return SUCCESS;
		} else {
			errorMsg = "PendingWork Exception occurs with job_code[" + job_code + "].";
			return "exception";
		}
	}
}
