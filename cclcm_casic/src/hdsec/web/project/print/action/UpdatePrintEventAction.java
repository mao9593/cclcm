package hdsec.web.project.print.action;

import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import javax.annotation.Resource;

/**
 * 修改打印作业
 * 
 * @author renmingfei
 * 
 */
public class UpdatePrintEventAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected LedgerService ledgerService;
	private String id = "";
	private PrintEvent event = null;
	private String update = "N";
	private String file_title = "";
	private int print_type;// 打印类型 普通，拼图，替换页打印
	private int part_num;// 打印份数
	private String PID_barcode = "";// 主台账条码信息
	private String PID_pagenum = "";// 主台账中被替换的页码
	private String PIDBarcode = "";
	private String PIDPagenum = "";
	private Integer page_count = null; // 页数

	public void setUpdate(String update) {
		this.update = update;
	}

	public PrintEvent getEvent() {
		return event;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title.replaceAll(" ", "").replaceAll(",", Constants.COMMON_SEPARATOR);
	}

	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public void setPID_barcode(String PID_barcode) {
		this.PID_barcode = PID_barcode;
	}

	public void setPID_pagenum(String PID_pagenum) {
		this.PID_pagenum = PID_pagenum;
	}

	public void setPIDBarcode(String PIDBarcode) {
		this.PIDBarcode = PIDBarcode;
	}

	public void setPIDPagenum(String PIDPagenum) {
		this.PIDPagenum = PIDPagenum;
	}

	public Integer getPage_count() {
		return page_count;
	}

	public void setPage_count(Integer page_count) {
		this.page_count = page_count;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {
			part_num = file_title.split(Constants.COMMON_SEPARATOR_REGEX).length;
			if (PID_barcode == null || PID_barcode.equals("")) {
				event = new PrintEvent(id, file_title, print_type, part_num, PIDBarcode, PIDPagenum);
			} else {
				event = new PrintEvent(id, file_title, print_type, part_num, PID_barcode, PID_pagenum);
			}

			String event_code = getCurUser().getUser_iidd() + System.currentTimeMillis();
			event.setEvent_code(event_code);
			event.setPage_count(page_count);
			printService.updatePrintEvent(event);

			insertCommonLog("修改打印作业[" + file_title + "]");
			return "ok";
		} else {
			event = printService.getPrintEventByPrintId(id);
			return SUCCESS;
		}
	}

	public int getPrint_type() {
		return print_type;
	}

	public void setPrint_type(int print_type) {
		this.print_type = print_type;
	}

	public int getPart_num() {
		return part_num;
	}

	public void setPart_num(int part_num) {
		this.part_num = part_num;
	}
}
