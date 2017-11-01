package hdsec.web.project.ledger.action;

import org.springframework.util.StringUtils;

/**
 * 取消光盘载体处理申请
 * 
 * @author lixiang
 * 
 */
public class CancelHandleCDAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String cd_id = "";
	private String type = "";
	private String device_code = "";

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(cd_id)) {
			ledgerService.cancelHandleCDById(cd_id);
			insertCommonLog("取消光盘载体处理申请[" + cd_id + "]");
		}
		if (StringUtils.hasLength(device_code)) {
			deviceService.cancelHandleDeviceByCode(device_code);
			insertCommonLog("取消磁介质载体处理申请[" + device_code + "]");
		}

		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
