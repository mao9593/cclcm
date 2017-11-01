package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchTypeName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageTemplateAction extends ArchBaseAction {
	private String type_name;
	private List<ArchTypeName> typeList = new ArrayList<ArchTypeName>();

	@Override
	public String executeFunction() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("type_name", type_name);
		typeList = archService.getTypeList(map);
		return SUCCESS;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public List<ArchTypeName> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<ArchTypeName> typeList) {
		this.typeList = typeList;
	}

}
