package hdsec.web.project.ledger.action;

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
 * 文件补打条码
 * 
 * @author chenrongrong 2015-4-9
 */
public class ReprintBarcodePaperAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList = null;
	private String paper_barcode = "";
	private String duty_user_name = "";
	private String seclv_code = "";
	private Date start_time = null;
	private Date end_time = null;
	private String user_name = "";
	private String dept_name = "";
	private String create_type = "";
	private List<SecLevel> seclvList = null;

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
		this.paper_barcode = paper_barcode;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
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

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public List<SecLevel> getSeclvList() {
		return seclvList = userService.getSecLevel();
	}

	public void setSeclvList(List<SecLevel> seclvList) {
		this.seclvList = seclvList;
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_print_result", true);
		map.put("paper_barcode", paper_barcode);
		map.put("duty_user_name", duty_user_name);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("admin_dept_ids_made", dept_ids);
		map.put("user_name", user_name);
		map.put("dept_name", dept_name);
		map.put("create_type", create_type);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllPaperLedgerSize(map);
		paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);
		return SUCCESS;
	}

}
