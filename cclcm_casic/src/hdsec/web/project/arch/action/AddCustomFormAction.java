package hdsec.web.project.arch.action;

import java.util.HashMap;
import java.util.Map;

public class AddCustomFormAction extends ArchBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String hiddens;
	private int archTypeId = 0;

	public int getArchTypeId() {
		return archTypeId;
	}

	public void setArchTypeId(int archTypeId) {
		this.archTypeId = archTypeId;
	}

	public String getHiddens() {
		return hiddens;
	}

	public void setHiddens(String hiddens) {
		this.hiddens = hiddens;
	}

	@Override
	public String executeFunction() throws Exception {
		String system_code = String.valueOf(System.currentTimeMillis());
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> archMap = new HashMap<String, Object>();
		map.put("SYSTEM_CODE", system_code);
		map.put("BARCODE", "载体条码^s^");
		map.put("DOS_NUM", "全宗号^s^");
		map.put("ARCH_NUM", "档号^s^");
		map.put("TYPE_CODE", "分类号^s^");
		map.put("FILE_TITLE", "文件标题^s^");
		map.put("FILE_NUM", "文件编号^s^");
		map.put("PAGE_NUM", "页数^i^");
		map.put("COUNT", "份数^i^");
		map.put("TOTAL_PAGE", "总页数^i^");
		map.put("SECLV_CODE", "密级^e^1");
		map.put("FILE_DATE", "归档日期^d^");
		map.put("FILE_CARR", "文件载体^e^3");
		map.put("KEEP_LIMIT", "保管期限^e^2");
		map.put("SUMM", "备注^s^");
		if (hiddens != null) {
			String[] custom = hiddens.split(",");
			for (int i = custom.length; i > 0; i--) {
				String keyName;
				if (i > 9) {
					keyName = "T" + i;
				} else {
					keyName = "T0" + i;
				}
				map.put(keyName, custom[i - 1]);
			}
			if (archTypeId != 0) {
				map.put("RECORD_COUNT", custom.length);
				archService.addArchKey(map);
				archMap.put("ID", archTypeId);
				archMap.put("TEMPLATE_ID", system_code);
				archService.updateArchType(archMap);
			}
		}
		if (!(hiddens == null)) {
			return "ok";
		}
		return SUCCESS;
	}
}
