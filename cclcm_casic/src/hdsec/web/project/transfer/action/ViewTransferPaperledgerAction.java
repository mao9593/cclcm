package hdsec.web.project.transfer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EntityStateEnum;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.decorator.CheckboxTableDecorator;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

public class ViewTransferPaperledgerAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList;
	private String paper_barcode;
	private String seclv_code;
	private Date start_time;
	private Date end_time;
	private String paper_state;
	private final String jobType = JobTypeEnum.TRANSFER.getJobTypeCode();
	private CheckboxTableDecorator decorator = null;

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
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

	public List<EntityStateEnum> getStateList() {
		/*
		 * if (getCurUser().is_cycleAdmin()) { return EntityStateEnum.getCycleAdminEntityStateList(); } else if
		 * (getCurUser().is_fileAdmin()) { return EntityStateEnum.getFileAdminEntityStateList(); } else return
		 * EntityStateEnum.getUserEntityStateList();
		 */
		return EntityStateEnum.getEntityStateList();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("paper_barcode", paper_barcode);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("paper_state", paper_state);
		map.put("is_transfer", true);
		map.put("scope", "PERSON");

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllPaperLedgerSize(map);
		paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);
		// if ((null == paperLedgerList && totalSize != 0) || totalSize > paperLedgerList.size()) {
		// paperLedgerList = ledgerService.getAllPaperLedgerList(map);
		// }
		decorator = new org.displaytag.decorator.CheckboxTableDecorator();
		decorator.setId("paper_id");
		decorator.setFieldName("_chk");
		return SUCCESS;
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

	public String getJobType() {
		return jobType;
	}

	public CheckboxTableDecorator getDecorator() {
		return decorator;
	}

}
