package hdsec.web.project.transfer.action;

import org.springframework.util.StringUtils;

/**
 * 删除流转作业
 * 
 * @author yy
 * 
 */
public class CanceltransferJobAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private String type;
	private String ajax_type = "";

	public void setAjax_type(String ajax_type) {
		this.ajax_type = ajax_type;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(job_code)) {
			transferService.cancelTransferEventByJobCode(job_code, type);
		}
		insertCommonLog("取消打印作业审批申请[" + job_code + "]");
		if ("cd".equals(type)) {
			return ajax_type.equalsIgnoreCase("ajax") ? null : "cd";
		} else {
			return ajax_type.equalsIgnoreCase("ajax") ? null : "paper";
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}
}
