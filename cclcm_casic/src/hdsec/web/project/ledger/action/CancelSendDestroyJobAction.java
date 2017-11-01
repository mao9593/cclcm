package hdsec.web.project.ledger.action;

import org.springframework.util.StringUtils;

/**
 * 删除送销任务
 * 
 * @author lixiang
 * 
 */
public class CancelSendDestroyJobAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private String type = "";

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(job_code)) {
			ledgerService.cancelSendDestroyEventByJobCode(job_code, type);
			if (type.equals("cd")) {
				insertCommonLog("取消光盘送销任务审批申请[" + job_code + "]");
			} else if (type.equals("paper")) {
				insertCommonLog("取消文件送销任务审批申请[" + job_code + "]");
			}

		}

		return null;
	}
}
