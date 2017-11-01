package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class DestroyPaperAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_codes;
	private String type = "N";

	@Override
	public String executeFunction() throws Exception {
		Date destroy_time = new Date();
		String[] ids = event_codes.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_ids", ids);
		map.put("destroy_time", destroy_time);
		map.put("duty_user_iidd", getCurUser().getUser_iidd());
		map.put("duty_user_name", getCurUser().getUser_name());
		map.put("duty_dept_id", getCurUser().getDept_id());
		map.put("duty_dept_name", getCurUser().getDept_name());
		ledgerService.destroyRetrievedPapers(map);
		for (String id : ids) {
			if (StringUtils.hasLength(id)) {
				EntityPaper paper = ledgerService.getPaperById(id);
				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(paper.getPaper_barcode());
				cycleitem.setEntity_type("PAPER");
				cycleitem.setOper_time(destroy_time);
				cycleitem.setUser_name(getCurUser().getUser_name());
				cycleitem.setDept_name(getCurUser().getDept_name());
				cycleitem.setOper("DESTORY");
				// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
				// 此处不用启动流程，job_code设置为default
				cycleitem.setJob_code("default");
				ledgerService.addCycleItem(cycleitem);
				insertCommonLog("销毁文件：条码[" + paper.getPaper_barcode() + "]");
			}
		}
		if (type.equals("Y")) {
			return "SELF";
		} else {
			return SUCCESS;
		}

	}

	public String getEvent_codes() {
		return event_codes;
	}

	public void setEvent_codes(String event_codes) {
		this.event_codes = event_codes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
