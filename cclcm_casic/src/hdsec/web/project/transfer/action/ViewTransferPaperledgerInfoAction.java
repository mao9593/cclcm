package hdsec.web.project.transfer.action;

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

public class ViewTransferPaperledgerInfoAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList;
	private String paper_barcode;
	private String duty_user_name;
	private String seclv_code;
	private Date start_time;
	private Date end_time;
	private String paper_state;
	
	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}
	
	public void setPaperLedgerList(List<EntityPaper> paperLedgerList) {
		this.paperLedgerList = paperLedgerList;
	}
	
	public String getPaper_barcode() {
		return paper_barcode;
	}
	
	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
	}
	
	public String getPaper_state() {
		return paper_state;
	}
	
	public void setPaper_state(String paper_state) {
		this.paper_state = paper_state;
	}
	
	public String getDuty_user_name() {
		return duty_user_name;
	}
	
	public String getSeclv_code() {
		return seclv_code;
	}
	
	public String getStart_time() {
		return sdf.format(start_time);
	}
	
	public String getEnd_time() {
		return sdf.format(end_time);
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	@Override
	public String executeFunction() throws Exception {
		// List<EntityPaper> entityList = new ArrayList<EntityPaper>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_barcode", paper_barcode);
		map.put("duty_user_name", duty_user_name);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("paper_state", paper_state);
		
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = transferService.getAllPaperLedgerSize(map);
		List<EntityPaper> papers = ledgerService.getAllPaperLedgerList(map, rbs);
		// if ((null == papers && totalSize != 0) || totalSize > papers.size()) {
		// papers = ledgerService.getAllPaperLedgerList(map);
		// }
		// for (EntityPaper entity : papers) {
		// entityList.add(entity);
		// }
		paperLedgerList = papers;
		return SUCCESS;
	}
	
	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name.trim();
	}
	
	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
}
