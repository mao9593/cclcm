package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchKey;

import java.util.HashMap;
import java.util.Map;

public class AddPropertyKeysAction extends ArchBaseAction {
	private String hiddens;
	private String template_id;

	public String getHiddens() {
		return hiddens;
	}

	public void setHiddens(String hiddens) {
		this.hiddens = hiddens;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	@Override
	public String executeFunction() throws Exception {
		ArchKey archKey = new ArchKey();
		archKey = archService.getTemplateBySystemCode(template_id);
		int count = archKey.getRecord_count();
		if (hiddens != null) {
			String[] custom = hiddens.split(",");
			for (int i = (custom.length + count), j = custom.length; i > count; j--, i--) {
				Map<String, Object> map = new HashMap<String, Object>();
				String keyName;
				if (i > 9) {
					keyName = "T" + i;
				} else {
					keyName = "T0" + i;
				}
				map.put("property_name", keyName);
				map.put("propertyValue", custom[j - 1]);
				map.put("template_id", template_id);
				archService.updateArchPropertyValue(map);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("property_name", "record_count");
			map.put("propertyValue", custom.length + count);
			map.put("template_id", template_id);
			archService.updateArchPropertyValue(map);

		}
		return SUCCESS;
	}

}
