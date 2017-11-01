package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.ExportPaperEnum;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出paper台账
 * 
 * @author yueying
 * 
 */
public class ExportPaperBorrowLedgerAction extends AutoCommonExportAction {
	private static final long serialVersionUID = 1L;

	private String paper_barcode;
	private String duty_user_name;
	private String seclv_code;
	private Date start_time;
	private Date end_time;
	private String paper_state;
	private String file_title;
	private final String filename = "Paper台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "Paper台账记录";
	private String cols= "";
	private List<String> tempTitles =new ArrayList<String>();

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}
	public void setTempTitles(List<String> tempTitles) {
		tempTitles.add("序号");
		String [] tempcols=cols.split(",");
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
		cols=basicService.getSysConfigItemValue("exportpaperborrowledger").getItem_value();
		setTempTitles(tempTitles);
		String []titles=(String[]) tempTitles.toArray(new String[tempTitles.size()]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("paper_barcode", paper_barcode);
		map.put("seclv_code", seclv_code);
		map.put("file_title", file_title);
		map.put("scope", "DEPT");
		map.put("duty_user_iidd", getSecUserFromSession().getUser_iidd());
		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "paperBorrow",cols);
		return null;
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

}
