package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.ExportPaperEnum;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 导出paper台账
 * 
 * @author yueying
 * 
 */
public class ExportDeptPaperLedgerAction extends AutoCommonExportAction {
	private static final long serialVersionUID = 1L;

	private String paper_barcode = "";
	private String duty_user_name = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private String paper_state = "";
	private String file_title = "";
	private String duty_dept_id = "";
	private String scope_dept_id;
	private String dept_ids = "";
	private String seclv_code1 = "";
	private String file_kind = "";
	private String is_book = "";
	private final String filename = "Paper台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "Paper台账记录";
	private String user_name = "";
	private String dept_name = "";
	private String cols = "";
	private List<String> tempTitles = new ArrayList<String>();
	private String is_security_result = "";

	public void setIs_security_result(String is_security_result) {
		this.is_security_result = is_security_result;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getIs_book() {
		return is_book;
	}

	public void setIs_book(String is_book) {
		this.is_book = is_book;
	}

	public String getFile_kind() {
		return file_kind;
	}

	public void setFile_kind(String file_kind) {
		this.file_kind = file_kind;
	}

	public void setStartTime(Date start_time) {
		this.startTime = start_time;
	}

	public void setEndTime(Date end_time) {
		this.endTime = end_time;
	}

	public String getSeclv_code1() {
		return seclv_code1;
	}

	public void setSeclv_code1(String seclv_code1) {
		this.seclv_code1 = seclv_code1;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setTempTitles(List<String> tempTitles) {
		tempTitles.add("序号");
		String[] tempcols = cols.split(",");
		for (String string : tempcols) {
			for (ExportPaperEnum item : ExportPaperEnum.values()) {
				if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
					tempTitles.add(item.getName());
					break;
				}
			}
		}
	}

	@Override
	public String executeFunction() throws Exception {
		cols = basicService.getSysConfigItemValue("exportdeptpaperledger").getItem_value();
		setTempTitles(tempTitles);
		String[] titles = tempTitles.toArray(new String[tempTitles.size()]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_barcode", paper_barcode);
		map.put("file_title", file_title);
		map.put("paper_state", paper_state);
		// map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("scope_dept", "DEPT");
        map.put("is_security_result", is_security_result);
		if (scope_dept_id != null && !(scope_dept_id.trim()).equals("")) {
			map.put("scope_dept_id", scope_dept_id);
		} else {
			map.put("scope_dept_id", null);
		}

		if (dept_ids != null && !(dept_ids.trim()).equals("")) {
			map.put("scope_dept_ids", dept_ids.split(","));
		} else {
			map.put("scope_dept_ids", null);
		}

		if (file_kind != null && !(file_kind.trim()).equals("")) {
			map.put("or_book", true);
		} else {
			map.put("not_book", true);
		}

		map.put("user_name", user_name);
		map.put("dept_name", dept_name);
		if (StringUtils.hasLength(seclv_code1)) {
			map.put("seclv_codes", seclv_code1.split(","));
		}
		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "paper", cols);
		return null;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public String getStartTime() {

		return sdf.format(startTime);
	}

	public String getEndTime() {
		return sdf.format(endTime);
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	public String getPaper_state() {
		return paper_state;
	}

	public void setPaper_state(String paper_state) {
		this.paper_state = paper_state;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getScope_dept_id() {
		return scope_dept_id;
	}

	public void setScope_dept_id(String scope_dept_id) {
		this.scope_dept_id = scope_dept_id;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids;
	}

}
