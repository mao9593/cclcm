package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EventModify;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 密级变更确认
 * 
 * @author congxue 2015-5-5
 */
public class ConfirmModifyAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String entity_type = "";
	private String result;
	private EventModify event;

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public EventModify getEvent() {
		return event;
	}

	public void setEvent(EventModify event) {
		this.event = event;
	}

	@Override
	public String executeFunction() throws Exception {
		// 更新event_modify表中的完成时间和变更状态
		Map<String, Object> updmap = new HashMap<String, Object>();
		updmap.put("event_code", event_code);
		updmap.put("finish_time", new Date());
		updmap.put("modify_status", "1");
		ledgerService.updateConfirmModifyEvent(updmap);
		// 查询变更记录
		event = ledgerService.getModifyEventByCode(event_code);
		// 查询目标密级和变更状态
		if (entity_type.equals("Paper")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paper_barcode", event.getBarcode());
			map.put("seclv_code", event.getTrg_seclv());
			map.put("paper_state", "0");// 状态改为留用
			// 确认密级变更后更改载体信息
			ledgerService.confirmEntityPaper(map);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cd_barcode", event.getBarcode());
			map.put("seclv_code", event.getTrg_seclv());
			map.put("cd_state", "0");// 状态改为留用
			// 确认密级变更后更改载体信息
			ledgerService.confirmEntityCD(map);
		}
		insertCommonLog("确认变更载体[" + event.getBarcode() + "]");
		result = "done";
		return SUCCESS;
	}

}
