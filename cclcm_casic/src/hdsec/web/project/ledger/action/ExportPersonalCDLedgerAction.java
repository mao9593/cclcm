package hdsec.web.project.ledger.action;

import hdsec.web.project.common.util.TimeUtil;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.ExportCDEnum;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 导出个人CD台账
 * 
 * @author yueying
 * 
 */
public class ExportPersonalCDLedgerAction extends AutoCommonExportAction {
	private static final long serialVersionUID = 1L;

	private List<EntityCD> cdLedgerList = null;
	private String cd_barcode = "";
	private String duty_user_name = "";
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private String cd_state;
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
		cols = basicService.getSysConfigItemValue("exportpersonalcdledger").getItem_value();
		setTempTitles(tempTitles);
		String[] titles = tempTitles.toArray(new String[tempTitles.size()]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getSecUserFromSession().getUser_iidd());
		map.put("cd_barcode", cd_barcode);
		map.put("cd_state", cd_state);
		// map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("scope", "PERSON");
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

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
