package hdsec.web.project.borrow.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.EntityCD;
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
 * 档案管理员归档光盘借阅交接确认
 * 
 * @author lixiang
 */
public class ConfirmFileCDBorrowAction extends BorrowBaseAction {
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
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

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
		map.put("cd_barcode", cd_barcode);
		map.put("seclv_code", seclv_code);
		map.put("file_list", file_list);
		map.put("scope", "PERSON");
		map.put("borrow_event", true);
		map.put("is_admin", true);
		map.put("file_dept_ids", dept_ids);
		if (update.equals("Y")) {
			Map<String, Object> mapOper = new HashMap<String, Object>();
			mapOper.put("cd_id", cd_id);
			RowBounds rbs = new RowBounds(beginIndex, pageSize);
			cdLedgerListOper = borrowService.getPersonCDBorrowLedgerList(mapOper, rbs);
			try {
				SecUser user = getCurUser();
				EntityCD cd = cdLedgerListOper.get(0);
				borrowService.saveFileCDConfirmRecord(cd, user, confirm_type);
			} catch (Exception e) {
				throw new Exception("光盘借用、归还操作失败，请重新尝试。");
			}
		}
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = borrowService.getPersonCDBorrowLedgerSize(map);
		cdLedgerList = borrowService.getPersonCDBorrowLedgerList(map, rbs);
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}