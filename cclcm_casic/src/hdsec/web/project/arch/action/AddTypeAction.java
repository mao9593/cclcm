package hdsec.web.project.arch.action;

import java.util.HashMap;
import java.util.Map;

public class AddTypeAction extends ArchBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String type_name;
	private String type_num;

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getType_num() {
		return type_num;
	}

	public void setType_num(String type_num) {
		this.type_num = type_num;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("TYPE_NAME", type_name);
		map.put("TYPE_NUM", type_num);
		archService.addType(map);
		return SUCCESS;
	}

}
