package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewSecLevelAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SecLevel> seclvList = null;
	private String is_sealed = "N";

	public String getIs_sealed() {
		return is_sealed;
	}

	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}

	public List<SecLevel> getSeclvList() {
		return seclvList;
	}

	public Integer getSeclvCount() {
		return userService.getSeclvCount();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_sealed", is_sealed);
		seclvList = userService.getSeclvList(map);
		return SUCCESS;
	}

}
