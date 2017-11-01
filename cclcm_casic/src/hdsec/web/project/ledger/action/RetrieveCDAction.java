package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecUser;

import org.springframework.util.StringUtils;

/**
 * 回收光盘：更新状态、回收人，全生命周期记录，交接确认
 * 
 * @author yueying
 */

public class RetrieveCDAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_codes;

	public String getEvent_codes() {
		return event_codes;
	}

	public void setEvent_codes(String event_codes) {
		this.event_codes = event_codes;
	}

	@Override
	public String executeFunction() throws Exception {
		SecUser user = getCurUser();
		for (String id : event_codes.split(",")) {
			if (StringUtils.hasLength(id)) {
				ledgerService.submitRetrieveCD(id, user);
				EntityCD cd = ledgerService.getCDById(id);
				insertCommonLog("回收光盘：条码[" + cd.getCd_barcode() + "]");
			}
		}
		return SUCCESS;
	}
}
