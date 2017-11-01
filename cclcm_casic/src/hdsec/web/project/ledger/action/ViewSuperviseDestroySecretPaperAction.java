package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
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

public class ViewSuperviseDestroySecretPaperAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String barcode = "";
	private String seclv_code = "";
	private Date start_time = null;
	private Date end_time = null;
	private String cur_user_iidd = "";

	private List<EntityPaper> paperLedgerList = null;
	private final JobTypeEnum jobType = JobTypeEnum.TRANSFER;

	public String getCur_user_iidd() {
		return cur_user_iidd;
	}

	public void setCur_user_iidd(String cur_user_iidd) {
		this.cur_user_iidd = cur_user_iidd;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode.trim();
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public void setPaperLedgerList(List<EntityPaper> paperLedgerList) {
		this.paperLedgerList = paperLedgerList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String temp = getCurUser().getUser_iidd();
		map.put("paper_barcode", barcode);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("job_status", "true");// 审批通过的paper
		map.put("paper_state", 4);// 类型为已销毁
		map.put("supervise_user_iidd", temp);
		map.put("scope", "DEPT");
		map.put("or_book", true);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllPaperLedgerSize(map);
		setPaperLedgerList(ledgerService.getAllPaperLedgerList(map, rbs));

		return SUCCESS;
	}

}
