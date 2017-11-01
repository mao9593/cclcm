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
 * 待回收paper台账管理列表
 * 
 * @author yueying
 * 
 */
public class ViewPaperAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList;
	private String paper_barcode = "";
	private String duty_user_name = "";
	private String seclv_code = "";
	private Date start_time;
	private Date end_time;
	private String paper_state = "";
	private String user_name = "";
	private String dept_name = "";
	private String hid_dept_ids = "";
	private String retrieveType = "";
	private String paper_barcodes = "";

	public String getHid_dept_ids() {
		return hid_dept_ids;
	}

	public void setHid_dept_ids(String hid_dept_ids) {
		this.hid_dept_ids = hid_dept_ids;
	}

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

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		hid_dept_ids = web_url;
		Map<String, Object> map = new HashMap<String, Object>();
		if ("批量扫描回收".equals(retrieveType) && paper_barcode != null && !"".equals(paper_barcode)) {
			paper_barcodes = paper_barcode + "," + paper_barcodes;
			map.put("barcodes", paper_barcodes.split(","));
			paper_barcode = "";
		} else {
			paper_barcodes = "";
			map.put("paper_barcode", paper_barcode);
		}
		map.put("duty_user_name", duty_user_name);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("paper_state", 7);// 申请销毁
		map.put("job_status", "true");// 审批通过的paper
		map.put("admin_dept_ids", dept_ids);
		map.put("user_name", user_name);
		map.put("dept_name", dept_name);
		map.put("start_time_sortable", true);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);

		// 中物五所文件回收处只有输入条码后点查询才显示台账
		// if (paper_barcode != null && !"".equals(paper_barcode)) {
		totalSize = ledgerService.getAllPaperLedgerSize(map);
		paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);
		// }

		// for (int i = 0; i < dept_ids.size(); i++) {
		// hid_dept_ids = hid_dept_ids + dept_ids.get(i) + ",";
		// }

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

	public String getRetrieveType() {
		return retrieveType;
	}

	public void setRetrieveType(String retrieveType) {
		this.retrieveType = retrieveType;
	}

	public String getPaper_barcodes() {
		return paper_barcodes;
	}

	public void setPaper_barcodes(String paper_barcodes) {
		this.paper_barcodes = paper_barcodes;
	}
}
