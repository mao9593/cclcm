package hdsec.web.project.ledger.action;

import org.springframework.util.StringUtils;

/**
 * 退回光盘回收申请
 * 
 * @author lixiang
 * 
 */
public class QuitHandleCDAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String cd_id = "";
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(cd_id)) {
			ledgerService.quitHandleCDById(cd_id, getCurUser());
		}
		insertCommonLog("退回光盘回收申请[" + cd_id + "]");
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
