package hdsec.web.project.print.action;

import hdsec.web.project.print.model.OaPrintEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改特殊打印作业详细
 * 
 * @author gaoyiming
 * 
 */
public class ChangeSpecialPrintEventDetailAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private Integer page_num = null; // 页数
	private Integer paper_num = null; // 份数
	private OaPrintEvent event = null;
	private String is_change = "N";

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public Integer getPage_num() {
		return page_num;
	}

	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}

	public Integer getPaper_num() {
		return paper_num;
	}

	public void setPaper_num(Integer paper_num) {
		this.paper_num = paper_num;
	}

	public String getIs_change() {
		return is_change;
	}

	public void setIs_change(String is_change) {
		this.is_change = is_change;
	}

	public OaPrintEvent getEvent() {
		return event;
	}

	public void setEvent(OaPrintEvent event) {
		this.event = event;
	}

	@Override
	public String executeFunction() throws Exception {
		if (is_change == "N") {
			event = printService.getSpecialPrintEventByEventCode(event_code);
			page_num = event.getPage_num();
			paper_num = event.getPaper_num();
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("event_code", event_code);
			map.put("page_num", page_num);
			map.put("paper_num", paper_num);
			printService.updateSpecialPrintEventByEventCode(map);
			event = printService.getSpecialPrintEventByEventCode(event_code);
		}
		return SUCCESS;
	}
}
