package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.ExportCDEnum;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出CD台账
 * 
 * @author yueying
 * 
 */
public class ExportCDBorrowLedgerAction extends AutoCommonExportAction {
	private static final long serialVersionUID = 1L;

	private String cd_barcode = "";
	private String duty_user_name = "";
	private String seclv_code = "";
	private Date start_time = null;
	private Date end_time = null;
	private String cd_state;
	private String file_list;
	private final String filename = "CD台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "CD台账记录";
	private String cols= "";
	private List<String> tempTitles =new ArrayList<String>();

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
		String [] tempcols=cols.split(",");
		for (String string : tempcols) {
			for (ExportCDEnum item : ExportCDEnum.values()) {
				if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
					tempTitles.add(item.getName());
					break;
				}
			}
		}
	}
	
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	@Override
	public String executeFunction() throws Exception {
		cols=basicService.getSysConfigItemValue("exportcdborrowledger").getItem_value();
		setTempTitles(tempTitles);
		String []titles=(String[]) tempTitles.toArray(new String[tempTitles.size()]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cd_barcode", cd_barcode);
		map.put("seclv_code", seclv_code);
		map.put("file_list", file_list);
		map.put("scope", "DEPT");
		map.put("duty_user_iidd", getSecUserFromSession().getUser_iidd());

		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "cdBorrow",cols);
		return null;
	}

	public String getCd_barcode() {
		return cd_barcode;
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
		this.duty_user_name = duty_user_name;
	}

	public String getCd_state() {
		return cd_state;
	}

	public void setCd_state(String cd_state) {
		this.cd_state = cd_state;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}
}
