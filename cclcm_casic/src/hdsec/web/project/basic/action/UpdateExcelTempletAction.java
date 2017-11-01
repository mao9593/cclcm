package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.ledger.model.ExportCDEnum;
import hdsec.web.project.ledger.model.ExportPaperEnum;

import java.util.List;

public class UpdateExcelTempletAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String update = "N";
	private String item_key;
	private String item_type;
	private String item_name;
	private String values;
	private SysConfigItem sysConfigItem;

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getItem_key() {
		return item_key;
	}

	public void setItem_key(String item_key) {
		this.item_key = item_key;
	}

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public SysConfigItem getSysConfigItem() {
		return sysConfigItem;
	}

	public void setSysConfigItem(SysConfigItem sysConfigItem) {
		this.sysConfigItem = sysConfigItem;
	}

	public String executeFunction() throws Exception {

		if (update.equals("Y")) {
			basicService.updateSysConfigItem(new SysConfigItem(item_key, 
					item_name, values, item_type, 0));
			return "ok";
		}else {
			if (item_type.equals("EXCELPAPER")) {
				sysConfigItem=basicService.getSysConfigItemValue(item_key);
				return "paper";
			}else {
				sysConfigItem=basicService.getSysConfigItemValue(item_key);
				return "cd";
			}
			
		}
	}
	
	public List<ExportPaperEnum> getTypeListPaper() {
		return ExportPaperEnum.getAllExportTypeList();
	}
	
	public List<ExportCDEnum> getTypeListCD() {
		return ExportCDEnum.getAllExportTypeList();
	}
}
