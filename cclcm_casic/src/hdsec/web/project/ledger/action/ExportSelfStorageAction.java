package hdsec.web.project.ledger.action;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 导出介质
 * 
 * @author yueying
 * 
 */
public class ExportSelfStorageAction extends CommonExportAction {
	private static final long serialVersionUID = 1L;
	
	private final String filename = "介质记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "介质记录";
	private String storage_barcode;
	private String seclv_code;
	private String med_type;
	private String storage_name;
	
	private final String[] titles = { "序号", "介质编号", "介质名称", "录入员", "设备所属部门", "设备编号",  "登记时间", "介质类型", "密级",
			"责任人", "使用人", "状态", "型号", "设备配置" };
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("use_user_iidd", getSecUserFromSession().getUser_iidd());
		map.put("storage_barcode", storage_barcode);
		map.put("seclv_code", seclv_code);
		map.put("med_type", med_type);
		map.put("storage_name", storage_name);
		
		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "storage");
		return null;
	}
	
	public String getStorage_barcode() {
		return storage_barcode;
	}
	
	public void setStorage_barcode(String storage_barcode) {
		this.storage_barcode = storage_barcode;
	}
	
	public String getSeclv_code() {
		return seclv_code;
	}
	
	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public String getMed_type() {
		return med_type;
	}
	
	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}
	
	public String getStorage_name() {
		return storage_name;
	}
	
	public void setStorage_name(String storage_name) {
		this.storage_name = storage_name;
	}
	
}
