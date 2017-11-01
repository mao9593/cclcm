package hdsec.web.project.ledger.action;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 导出device台账
 * 
 * @author yueying
 * 
 */
public class ExportPersonalDeviceLedgerAction extends CommonExportAction {
	private static final long serialVersionUID = 1L;
	
	private final String filename = "磁介质台账记录-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "磁介质台账记录";
	private final String[] titles = { "序号", "磁介质编号", "磁介质名称", "使用人名称", "使用人部门", "登记时间", "介质类型", "密级", "责任人", "说明",
			"状态", "型号", "设备配置" };
	private String device_barcode;
	private String seclv_code;
	private String med_type;
	private String device_status;
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("borrow_user_iidd", getSecUserFromSession().getUser_iidd());
		map.put("device_barcode", device_barcode);
		map.put("seclv_code", seclv_code);
		map.put("med_type", med_type);
		map.put("device_status", device_status);
		
		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "device");
		return null;
	}
	
	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}
	
	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}
	
	public void setDevice_status(String device_status) {
		this.device_status = device_status;
	}
	
}
