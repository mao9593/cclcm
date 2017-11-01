package hdsec.web.project.ledger.action;

import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.ledger.model.ExportEfileInputEnum;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ExportDeptEfileInputLedgerAction extends AutoCommonExportAction {
	private static final long serialVersionUID = 1L;

	private String duty_user_name;
	private String seclv_code;
	private Date startTime;
	private Date endTime;
	private String job_status = "";
	private String file_title;
	private String duty_dept_id;
	private String user_iidd = "";
	public List<String> dept_ids = new ArrayList<String>();
	private String dept_id = "";
	private final String filename = "EfileInput台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "EfileInput台账记录";
	private String cols = "";
	private List<String> tempTitles = new ArrayList<String>();
	private String usage_code = "";

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setTempTitles(List<String> tempTitles) {
		tempTitles.add("序号");
		String[] tempcols = cols.split(",");
		for (String string : tempcols) {
			for (ExportEfileInputEnum item : ExportEfileInputEnum.values()) {
				if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
					tempTitles.add(item.getName());
					break;
				}
			}
		}
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
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

	@Override
	public String executeFunction() throws Exception {
		cols = basicService.getSysConfigItemValue("exportefileinputledger").getItem_value();
		setTempTitles(tempTitles);
		String[] titles = tempTitles.toArray(new String[tempTitles.size()]);
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("file_title", file_title);
		map.put("user_iidd", user_iidd);
		map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("job_status", job_status);
		map.put("dept_id", duty_dept_id);
		map.put("duty_user_name", duty_user_name);
		if (StringUtils.hasLength(dept_id)) {
			List<String> deptIds = StringUtil.stringArrayToList(dept_id.split(","));
			getDeptId(deptIds);
			map.put("dept_ids", dept_ids);
		}
		map.put("usage_code", usage_code);

		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "efileinput", cols);

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
