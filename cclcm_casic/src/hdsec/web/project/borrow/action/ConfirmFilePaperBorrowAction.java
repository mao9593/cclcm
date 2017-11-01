package hdsec.web.project.borrow.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 档案管理员归档文件借阅交接确认
 * 
 * @author lixiang
 */
public class ConfirmFilePaperBorrowAction extends BorrowBaseAction {
	private static final long serialVersionUID = 1L;
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
	private String type = "";

	public void setType(String type) {
		this.type = type;
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
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
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
		map.put("scope", "PERSON");
		map.put("borrow_event", true);
		map.put("is_admin", true);
		map.put("file_dept_ids", dept_ids);
		if (update.equals("Y")) {
			Map<String, Object> mapOper = new HashMap<String, Object>();
			mapOper.put("paper_id", paper_id);
			RowBounds rbs = new RowBounds(beginIndex, pageSize);
			paperLedgerListOper = borrowService.getPersonPaperBorrowLedgerList(mapOper, rbs);
			try {
				SecUser user = getCurUser();
				EntityPaper paper = paperLedgerListOper.get(0);
				borrowService.saveFilePaperConfirmRecord(paper, user, confirm_type);
			} catch (Exception e) {
				throw new Exception("文件借用、归还操作失败，请重新尝试。");
			}
		}
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = borrowService.getPersonPaperBorrowLedgerSize(map);
		paperLedgerList = borrowService.getPersonPaperBorrowLedgerList(map, rbs);
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
