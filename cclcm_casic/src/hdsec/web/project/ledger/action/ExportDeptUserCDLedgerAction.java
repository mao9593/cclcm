package hdsec.web.project.ledger.action;

import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.ExportCDEnum;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.user.service.UserService;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

/**
 * 导出CD台账
 * 
 * @author yueying
 * 
 */
public class ExportDeptUserCDLedgerAction extends AutoCommonExportAction {
	private static final long serialVersionUID = 1L;

	@Resource
	protected UserService userService;
	@Resource
	protected LedgerService ledgerService;

	private List<EntityCD> cdLedgerList = null;
	private String cd_barcode = "";
	private String duty_user_name = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private String cd_state;
	private String file_list;
	private String duty_dept_id;
	private String create_type;
	private String dept_ids;
	private String conf_code = "";
	private Integer expire_status = null;
	private final String filename = "CD台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "CD台账记录";
	private String cols = "";
	private List<String> tempTitles = new ArrayList<String>();

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public List<String> getTempTitles() {
		return tempTitles;
	}

	public void setTempTitles(List<String> tempTitles) {
		tempTitles.add("序号");
		String[] tempcols = cols.split(",");
		for (String string : tempcols) {
			for (ExportCDEnum item : ExportCDEnum.values()) {
				if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
					tempTitles.add(item.getName());
					break;
				}
			}
		}
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<EntityCD> getCDLedgerList() {
		return cdLedgerList;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public Integer getExpire_status() {
		return expire_status;
	}

	public void setExpire_status(Integer expire_status) {
		this.expire_status = expire_status;
	}

	@Override
	public String executeFunction() throws Exception {
		cols = basicService.getSysConfigItemValue("exportdeptusercdledger").getItem_value();
		setTempTitles(tempTitles);
		String[] titles = tempTitles.toArray(new String[tempTitles.size()]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cd_barcode", cd_barcode);
		map.put("file_list", file_list);
		map.put("cd_state", cd_state);
		// map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("scope_dept", "PERSON");
		map.put("duty_dept_id", duty_dept_id);
		map.put("duty_user_name", duty_user_name);
		map.put("create_type", create_type);
		map.put("conf_code", conf_code);
		map.put("admin_dept_ids", dept_ids.split(","));
		map.put("expire_status", expire_status);
		map.put("expire_time", new Date());
		if (StringUtils.hasLength(seclv_code)) {
			map.put("seclv_codes", seclv_code.split(","));
		}
		map.put("coming_expire_time", TimeUtil.getAfterXDay(2));
		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "cd", cols);
		return null;
	}

	public String getCd_barcode() {
		return cd_barcode;
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

	public String getCd_state() {
		return cd_state;
	}

	public void setCd_state(String cd_state) {
		this.cd_state = cd_state;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
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

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code.trim();
	}

}
