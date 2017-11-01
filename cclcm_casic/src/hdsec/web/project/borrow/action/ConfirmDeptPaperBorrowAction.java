package hdsec.web.project.borrow.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.enter.service.EnterService;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 部门文件借用确认
 * 
 * @author congxue 2016-10-27
 */
public class ConfirmDeptPaperBorrowAction extends BorrowBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	protected EnterService enterService;
	private List<EntityPaper> paperLedgerList = null;
	private List<EntityPaper> paperLedgerListOper = null;
	private String paper_barcode = "";
	private Integer seclv_code = null;
	private String file_title = "";
	private String scope = "";
	private final String jobType = JobTypeEnum.BORROW.getJobTypeCode();
	private String update = "";
	private String paper_id = "";
	private String confirm_type = "";
	private final String confirmflag = "";
	private String event_code = "";

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

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

	public String getUpdate() {
		return update;
	}

	public String getPaper_id() {
		return paper_id;
	}

	public String getConfirm_type() {
		return confirm_type;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
	}

	public void setConfirm_type(String confirm_type) {
		this.confirm_type = confirm_type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getConfirmflag() {
		return confirmflag;
	}

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<EntityPaper> getPaperLedgerListOper() {
		return paperLedgerListOper;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equals("Y")) {
			Map<String, Object> mapOper = new HashMap<String, Object>();
			mapOper.put("paper_id", paper_id);
			mapOper.put("duty_user_iidd", curUser.getUser_iidd());
			mapOper.put("duty_user_name", curUser.getUser_name());
			mapOper.put("duty_dept_id", curUser.getDept_id());
			mapOper.put("duty_dept_name", curUser.getDept_name());
			borrowService.updatePaperLedger(mapOper);
			mapOper.put("is_sure", "Y");
			mapOper.put("event_code", event_code);
			borrowService.updateIsSureByEventCode(mapOper);
		}
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
		map.put("is_admin", true);
		map.put("applyuserid", curUser.getUser_iidd());
		map.put("borrow_status", "1");
		map.put("paper_state", "6");
		map.put("is_sure", "N");
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = borrowService.getPersonPaperBorrowLedgerSize(map);
		paperLedgerList = borrowService.getPersonPaperBorrowLedgerList(map, rbs);
		return SUCCESS;
	}
}
