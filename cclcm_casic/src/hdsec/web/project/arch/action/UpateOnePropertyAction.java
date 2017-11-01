package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchKey;

import java.util.HashMap;
import java.util.Map;

public class UpateOnePropertyAction extends ArchBaseAction {
	private String property_name;
	private String template_id;
	private String name;
	private String type;
	private String dirc;
	private String type_name;

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDirc() {
		return dirc;
	}

	public void setDirc(String dirc) {
		this.dirc = dirc;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
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
		String propertyValue = name + "^" + type + "^";
		if (type.equals("e")) {
			propertyValue += dirc;
		}
		// archKey = archService.getArchKey(template_id);
		Map<String, Object> map = putProperty(archKey);
		map.put("property_name", property_name);
		map.put("propertyValue", propertyValue);
		map.put("template_id", template_id);
		archService.updateArchPropertyValue(map);
		return SUCCESS;
	}

	private String typeChangeTypeName(String type) {
		String type_name = null;
		switch (type) {
		case "s":
			type_name = "文　字";
			break;
		case "i":
			type_name = "数　值";
			break;
		case "d":
			type_name = "日　期";
			break;
		case "e":
			type_name = "下划框";
			break;
		default:
			break;
		}
		return type_name;
	}

	private Map<String, Object> putProperty(ArchKey archKey) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("T01", archKey.getT01());
		map.put("T02", archKey.getT02());
		map.put("T03", archKey.getT03());
		map.put("T04", archKey.getT04());
		map.put("T05", archKey.getT05());
		map.put("T06", archKey.getT06());
		map.put("T07", archKey.getT07());
		map.put("T08", archKey.getT08());
		map.put("T09", archKey.getT09());
		map.put("T10", archKey.getT10());
		map.put("T11", archKey.getT11());
		map.put("T12", archKey.getT12());
		map.put("T13", archKey.getT13());
		map.put("T14", archKey.getT14());
		map.put("T15", archKey.getT15());
		map.put("T16", archKey.getT16());
		map.put("T17", archKey.getT17());
		map.put("T18", archKey.getT18());
		map.put("T19", archKey.getT19());
		map.put("T20", archKey.getT20());
		map.put("T21", archKey.getT21());
		map.put("T22", archKey.getT22());
		map.put("T23", archKey.getT23());
		map.put("T24", archKey.getT24());
		map.put("T25", archKey.getT25());
		return map;
	}
}
