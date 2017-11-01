package hdsec.web.project.disc.action;

import hdsec.web.project.user.model.SecUser;

import java.util.HashMap;
import java.util.Map;

/**
 * 退还
 * 
 * @author zp
 */
public class SpaceCdReturnAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		SecUser user = getCurUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		discService.updateSpaceCdEvent(map);
		discService.deleteSpaceCdEntityById(id);
		return SUCCESS;
	}
}
