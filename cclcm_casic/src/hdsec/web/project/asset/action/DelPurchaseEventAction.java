package hdsec.web.project.asset.action;

/**
 * 删除未提交的资产采购作业
 * 
 * @author gaoximin 2015-7-13
 */
public class DelPurchaseEventAction extends AssetBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String eventtype = "";

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	@Override
	public String executeFunction() throws Exception {
		if (eventtype.equals("del")) {
			assetService.delPurchaseEventByEnterCode(event_code, eventtype);
			insertCommonLog("删除资产采购作业[" + event_code + "]");
		}
		if (eventtype.equals("PURCHASE")) {
			assetService.delPurchaseEventByEnterCode(event_code, "");
			insertCommonLog("撤销资产采购作业[" + event_code + "]");
		}
		if (eventtype.equals("STORE")) {
			assetService.delStorageEventByEnterCode(event_code);
			insertCommonLog("撤销资产入库作业[" + event_code + "]");
		}
		if (eventtype.equals("wastechange")) {
			assetService.delWasteChangeEventByEnterCode(event_code);
			insertCommonLog("撤销资产报废变更作业[" + event_code + "]");
		}
		return SUCCESS;
	}
}
