package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 查看闭环申请记录
 * 
 * @author renmingfei
 * 
 */
public class ManageHandlePaperAction extends LedgerBaseAction {
	
	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList = null;
	private String paper_barcode = "";
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String file_title = "";
	private String printer_code = "";
	private String job_status = "";
	
	public String getPaper_barcode() {
		return paper_barcode;
	}
	
	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
	}
	
	public Integer getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public String getStartTime() {
		return startTime == null ? "" : sdf.format(startTime);
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	public String getEndTime() {
		return endTime == null ? "" : sdf.format(endTime);
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getFile_title() {
		return file_title;
	}
	
	public void setFile_title(String file_title) {
		this.file_title = file_title.trim();
	}
	
	public String getPrinter_code() {
		return printer_code;
	}
	
	public void setPrinter_code(String printer_code) {
		this.printer_code = printer_code;
	}
	
	public String getJob_status() {
		return job_status;
	}
	
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}
	
	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	public List<SysPrinter> getPrinterList() {
		return basicService.getSysPrinterList();
	}
	
	@Override
	public String executeFunction() throws Exception {
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("paper_barcode", paper_barcode);
		map.put("printer_code", printer_code);
		map.put("file_title", file_title);
		map.put("job_status", job_status);
		map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("has_job_code", true);
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllPaperLedgerSize(map);
		paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PAPER", 3);
		return SUCCESS;
	}
	
}
