package hdsec.web.project.securitydept.action;

import java.util.HashMap;
import java.util.Map;

public class DeleteSecurityDeptAction extends DeptBaseAction {

	private Integer id = -1;
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		securityDeptService.delSecurityUser(map);
		return SUCCESS;
	}

}
