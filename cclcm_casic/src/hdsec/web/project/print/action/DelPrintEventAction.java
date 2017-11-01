package hdsec.web.project.print.action;

import hdsec.web.project.print.model.PrintEvent;

import org.springframework.util.StringUtils;

/**
 * 删除打印作业/草稿
 * 
 * @author renmingfei
 * 
 */
public class DelPrintEventAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String id = "";
	private PrintEvent event = null;
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (type.equals("F")) {
			if (StringUtils.hasLength(id)) {
				printService.delFixedAccordingById(id);
				return "fix";
			} else {
				throw new Exception("要删除的<定密依据>任务ID不存在或者已经被删除");
			}
		}
		if (StringUtils.hasLength(id)) {
			event = printService.getPrintEventByPrintId(id);
			printService.delPrintEventByPringId(id);
		} else {
			event = printService.getPrintEventByPrintCode(event_code);
			printService.delPrintEventByPrintCode(event_code);
		}
		if (event == null) {
			throw new Exception("要删除的作业不存在或者已经被删除");
		}
		insertCommonLog("删除打印作业[" + event.getFile_title() + "]");
		return SUCCESS;
	}
}
