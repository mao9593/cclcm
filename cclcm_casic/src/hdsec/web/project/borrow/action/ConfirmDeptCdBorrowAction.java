package hdsec.web.project.borrow.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 部门光盘借用确认
 * 
 * @author congxue 2016-10-27
 */
public class ConfirmDeptCdBorrowAction extends BorrowBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityCD> cdLedgerList = null;
	private List<EntityCD> cdLedgerListOper = null;
	private String cd_barcode = "";
	private Integer seclv_code = null;
	private String scope = "";
	private String file_list = "";
	private final String jobType = JobTypeEnum.BORROW.getJobTypeCode();
	private String update = "";
	private String cd_id = "";
	private String confirm_type = "";
	private String event_code = "";

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getJobType() {
		return jobType;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
	}

	public String getConfirm_type() {
		return confirm_type;
	}

	public void setConfirm_type(String confirm_type) {
		this.confirm_type = confirm_type;
	}

	public List<EntityCD> getCdLedgerList() {
		return cdLedgerList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<EntityCD> getCdLedgerListOper() {
		return cdLedgerListOper;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equals("Y")) {
			Map<String, Object> mapOper = new HashMap<String, Object>();
			mapOper.put("cd_id", cd_id);
			mapOper.put("duty_user_iidd", curUser.getUser_iidd());
			mapOper.put("duty_user_name", curUser.getUser_name());
			mapOper.put("duty_dept_id", curUser.getDept_id());
			mapOper.put("duty_dept_name", curUser.getDept_name());
			borrowService.updateCdLedger(mapOper);
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
		map.put("cd_barcode", cd_barcode);
		map.put("seclv_code", seclv_code);
		map.put("file_list", file_list);
		map.put("scope", "DEPT");
		map.put("is_admin", true);
		map.put("applyuserid", curUser.getUser_iidd());
		map.put("borrow_status", "1");
		map.put("cd_state", "6");
		map.put("is_sure", "N");
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = borrowService.getPersonCDBorrowLedgerSize(map);
		cdLedgerList = borrowService.getPersonCDBorrowLedgerList(map, rbs);
		return SUCCESS;
	}

}
