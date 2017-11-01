package hdsec.web.project.enter.action;

import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

public class EnterCDHistoryAction extends EnterBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityCD> cdLedgerList;
	private String cd_barcode;
	private String seclv_code;
	private Date start_time;
	private Date end_time;
	private String cd_state;
	private String dept_id = "";
	private String dept_name = "";
	private String user_id = "";

	public List<EntityCD> getCdLedgerList() {
		return cdLedgerList;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}

	public String getCd_state() {
		return cd_state;
	}

	public void setCd_state(String cd_state) {
		this.cd_state = cd_state;
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
		return userService.getImportSecLevelByUser(getCurUser().getUser_iidd());
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

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cd_barcode", cd_barcode);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("create_type", "LEADIN");
		map.put("admin_dept_ids_made", dept_ids);
		map.put("dept_id", dept_id);
		map.put("dept_name", dept_name);
		map.put("user_id", user_id);
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllCDLedgerSize(map);
		cdLedgerList = ledgerService.getAllCDLedgerList(map, rbs);

		return SUCCESS;
	}
}
