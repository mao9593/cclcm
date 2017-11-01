package hdsec.web.project.ledger.action;

import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.ledger.model.ExportPaperEnum;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 导出部门用户纸质台账
 * 
 * @author yueying
 * 
 */
public class ExportDeptUserPaperLedgerAction extends AutoCommonExportAction {
	private static final long serialVersionUID = 1L;

	private String paper_barcode;
	private String duty_user_name;
	private String seclv_code;
	private Date startTime;
	private Date endTime;
	private String paper_state;
	private String file_title;
	private String duty_dept_id;
	private String create_type;
	private String dept_ids;
	private String keyword_content = "";
	private Integer expire_status = null;
	private final String filename = "Paper台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "Paper台账记录";
	private String cols = "";
	private List<String> tempTitles = new ArrayList<String>();

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

	public void setStartTime(Date start_time) {
		this.startTime = start_time;
	}

	public void setEndTime(Date end_time) {
		this.endTime = end_time;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
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
		this.duty_user_name = duty_user_name.trim();
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

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title.trim();
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public String getDept_ids() {
		return dept_ids;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids;
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

	@Override
	public String executeFunction() throws Exception {
		cols = basicService.getSysConfigItemValue("exportdeptuserpaperledger").getItem_value();
		setTempTitles(tempTitles);
		String[] titles = tempTitles.toArray(new String[tempTitles.size()]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_barcode", paper_barcode);
		map.put("file_title", file_title);
		map.put("paper_state", paper_state);
		// map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("scope_dept", "PERSON");
		map.put("duty_dept_id", duty_dept_id);
		map.put("duty_user_name", duty_user_name);
		map.put("create_type", create_type);
		map.put("admin_dept_ids", dept_ids.split(","));
		map.put("keyword_content", keyword_content);
		map.put("expire_status", expire_status);
		map.put("expire_time", new Date());
		if (StringUtils.hasLength(seclv_code)) {
			map.put("seclv_codes", seclv_code.split(","));
		}
		map.put("coming_expire_time", TimeUtil.getAfterXDay(2));

		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "paper", cols);
		return null;
	}
}
