package hdsec.web.project.ledger.action;

import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.ledger.model.ExportPaperEnum;
import hdsec.web.project.user.model.SecDept;

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
public class ExportPaperLedgerAction extends AutoCommonExportAction {
	private static final long serialVersionUID = 1L;

	private String paper_barcode = "";
	private String duty_user_name = "";
	private String duty_user_iidd = "";
	private String seclv_code = "";
	private Date start_time;
	private Date end_time;
	private String dept_id = "";
	private String create_type = "";
	private String file_title = "";
	private String paper_state = "";
	private String keyword_content = "";
	private Integer expire_status = null;
	private final String filename = "Paper台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "Paper台账记录";
	public List<String> dept_ids = new ArrayList<String>();
	private String cols = "";
	private List<String> tempTitles = new ArrayList<String>();
	private String duty_dept_id = "";
	private String user_name = "";
	private String dept_iidd = "";
	private String chooseChildDept = "";

	public String getChooseChildDept() {
		return chooseChildDept;
	}

	public void setChooseChildDept(String chooseChildDept) {
		this.chooseChildDept = chooseChildDept;
	}

	public String getDept_iidd() {
		return dept_iidd;
	}

	public void setDept_iidd(String dept_iidd) {
		this.dept_iidd = dept_iidd;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title.trim();
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

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name.trim();
	}

	public String getDuty_user_iidd() {
		return duty_user_iidd;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
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

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public void setKeyword_content(String keyword_content) {
		this.keyword_content = keyword_content.trim();
	}

	public Integer getExpire_status() {
		return expire_status;
	}

	public void setExpire_status(Integer expire_status) {
		this.expire_status = expire_status;
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
		String dept_cs = "";
		if (!duty_dept_id.equals("") && duty_dept_id != null) {
			SecDept secDept = userService.getSecDeptById(duty_dept_id);
			dept_cs = secDept.getDept_cs();

		}

		cols = basicService.getSysConfigItemValue("exportpaperledger").getItem_value();
		setTempTitles(tempTitles);
		String[] titles = tempTitles.toArray(new String[tempTitles.size()]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_barcode", paper_barcode);
		map.put("duty_user_name", duty_user_name);
		map.put("duty_user_iidd", duty_user_iidd);

		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("chooseChildDept", chooseChildDept);
		map.put("file_title", file_title);
		map.put("paper_state", paper_state);
		map.put("create_type", create_type);
		// map.put("keyword_content", keyword_content);
		map.put("expire_status", expire_status);
		map.put("expire_time", new Date());
		map.put("user_name", user_name);
		map.put("dept_CS", dept_cs);
		// map.put("duty_dept_id", duty_dept_id);
		map.put("dept_iidd", dept_iidd);
		map.put("coming_expire_time", TimeUtil.getAfterXDay(2));
		if (StringUtils.hasLength(dept_id)) {
			List<String> deptIds = StringUtil.stringArrayToList(dept_id.split(","));
			getDeptId(deptIds);
			map.put("dept_ids", dept_ids);
		}
		if (StringUtils.hasLength(seclv_code)) {
			map.put("seclv_codes", seclv_code.split(","));
		}
		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "secpaper", cols);
		return null;
	}

	public List<String> getList(String id) {
		List<String> str = new ArrayList<String>();
		str = ledgerService.getDeptIdByParentId(id);
		return str;
	}

	public void getDeptId(List<String> ids) {
		if (ids != null || ids.size() > 0) {
			for (String id : ids) {
				dept_ids.add(id);
				List<String> list = getList(id);
				getDeptId(list);
			}
		}
		return;
	}
}
