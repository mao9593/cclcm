package hdsec.web.project.borrow.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
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
 * 部门文件载体展示，提交借阅申请 2014-4-16 上午8:13:38
 * 
 * @author gaoximin
 */
public class ViewBorrowPaperAction extends BorrowBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList = null;
	private String paper_barcode = "";
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String file_title = "";
	private String scope = "";
	private final String jobType = JobTypeEnum.BORROW.getJobTypeCode();

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

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getJobType() {
		return jobType;
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
		map.put("paper_barcode", paper_barcode);
		map.put("seclv_code", seclv_code);
		map.put("file_title", file_title);
		map.put("scope", "DEPT");
		map.put("current_dept_id", getCurUser().getDept_id());
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		// map.put("paper_state", "0");
		map.put("not_book", true);
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllPaperLedgerSize(map);
		paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);
		return SUCCESS;
	}
}
