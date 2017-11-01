package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.ledger.model.ExportCDEnum;
import hdsec.web.project.ledger.model.ExportPaperEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 用途管理列表
 * 
 * @author renmingfei
 * 
 */
public class ConfigExcelTempletAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysConfigItem> excelConfigs = new ArrayList<SysConfigItem>();
	private List<SysConfigItem> tempExcelConfigs = new ArrayList<SysConfigItem>();

	public List<SysConfigItem> getExcelConfigs() {
		return excelConfigs;
	}

	public void setExcelConfigs(List<SysConfigItem> excelConfigs) {
		this.excelConfigs = excelConfigs;
	}
	
	public String getCDName(String value) {
		String [] tempcols=value.split(",");
		StringBuffer sb = new StringBuffer();
		for (String string : tempcols) {
			for (ExportCDEnum item : ExportCDEnum.values()) {
				if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
					sb.append(item.getName()).append(",");
					break;
				}
			}
		}
		
		return sb.toString();
	}
	public String getPAPERName(String value) {
		String [] tempcols=value.split(",");
		StringBuffer sb = new StringBuffer();
		for (String string : tempcols) {
			for (ExportPaperEnum item : ExportPaperEnum.values()) {
				if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
					sb.append(item.getName()).append(",");
					break;
				}
			}
		}
		
		return sb.toString();
	}

	public String executeFunction() throws Exception {
		tempExcelConfigs=basicService.getSysConfigItems("EXCELPAPER");
		if (tempExcelConfigs!=null&&tempExcelConfigs.size()>0) {
			for (SysConfigItem item : tempExcelConfigs) {
				item.setItem_value_name(getPAPERName(item.getItem_value()));
				excelConfigs.add(item);
			}
		}
		tempExcelConfigs=basicService.getSysConfigItems("EXCELCD");
		if (tempExcelConfigs!=null&&tempExcelConfigs.size()>0) {
			for (SysConfigItem item : tempExcelConfigs) {
				item.setItem_value_name(getCDName(item.getItem_value()));
				excelConfigs.add(item);
			}
		}
		return SUCCESS;
	}
}
