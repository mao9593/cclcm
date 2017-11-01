package hdsec.web.project.securityuser.action;

import java.util.HashMap;
import java.util.Map;

public class DeleteCompanycontanctAction extends SecurityUserBaseAction {

	private static final long serialVersionUID = 1L;
	private Integer id = -1;

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		securityUserService.delCompanyContact(map);
		return SUCCESS;
	}

}
