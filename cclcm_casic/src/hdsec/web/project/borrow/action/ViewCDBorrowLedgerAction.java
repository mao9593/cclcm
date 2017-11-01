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
 * 个人光盘借阅台帐 2014-4-23 上午7:04:49
 * 
 * @author gaoximin
 */
public class ViewCDBorrowLedgerAction extends BorrowBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityCD> cdLedgerList = null;
	private String cd_barcode = "";
	private String seclv_code = "";
	private String scope = "";
	private String file_list = "";
	private final String jobType = JobTypeEnum.BORROW.getJobTypeCode();

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
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
		this.file_list = file_list.trim();
	}

	public String getJobType() {
		return jobType;
	}

	public List<EntityCD> getCdLedgerList() {
		return cdLedgerList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
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
		map.put("duty_user_iidd", getCurUser().getUser_iidd());
		map.put("borrow_event", true);
		map.put("borrowing", true);
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = borrowService.getPersonCDBorrowLedgerSize(map);
		cdLedgerList = borrowService.getPersonCDBorrowLedgerList(map, rbs);
		return SUCCESS;
	}
}
