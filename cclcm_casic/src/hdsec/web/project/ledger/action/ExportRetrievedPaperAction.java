package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.ExportPaperEnum;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 导出已回收paper台账
 * 
 * @author lixiang
 * 
 */
public class ExportRetrievedPaperAction extends AutoCommonExportAction {
	private static final long serialVersionUID = 1L;

	private String paper_barcode = "";
	private String duty_user_name = "";
	private String seclv_code = "";
	private Date start_time;
	private Date end_time;
	private String user_name;
	private String dept_name;
	private String retrieve_box_code;// 回收柜
	private String dept_ids = "";
	private final String filename = "已回收文件台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "已回收文件台账记录";
	private String cols= "";
	private List<String> tempTitles =new ArrayList<String>();
	private String retrieveType = "";
	private String paper_barcodes = "";
	
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

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name.trim();
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getStart_time() {
		return sdf.format(start_time);
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return sdf.format(end_time);
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getRetrieve_box_code() {
		return retrieve_box_code;
	}

	public void setRetrieve_box_code(String retrieve_box_code) {
		this.retrieve_box_code = retrieve_box_code;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids;
	}
	
	public void setRetrieveType(String retrieveType) {
		this.retrieveType = retrieveType;
	}

	public void setPaper_barcodes(String paper_barcodes) {
		this.paper_barcodes = paper_barcodes;
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
		cols=basicService.getSysConfigItemValue("exportretrievedpaper").getItem_value();
		setTempTitles(tempTitles);
		String []titles=(String[]) tempTitles.toArray(new String[tempTitles.size()]);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_sendes", true);
		if("批量扫描回收".equals(retrieveType)){
			map.put("barcodes", paper_barcodes.split(","));
		}else{
			paper_barcodes = "";
			map.put("paper_barcode", paper_barcode);
		}
		map.put("duty_user_name", duty_user_name);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("retrieve_box_code", retrieve_box_code);
		map.put("admin_dept_ids", dept_ids.split(","));
		map.put("retrieve_time_sortable", true);
		map.put("user_name", user_name);
		map.put("dept_name", dept_name);
		map.put("time_type", "retrieve");
		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "paper",cols);
		return null;
	}
}
