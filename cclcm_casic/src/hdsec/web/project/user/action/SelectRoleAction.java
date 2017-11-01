package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecRole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 选择用户角色用于添加审批节点
 * @author renmingfei
 *
 */
public class SelectRoleAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private List<SecRole> roleList = null;
	
	public List<SecRole> getRoleList() {
		return roleList;
	}
	
	@Override
	public String executeFunction() throws Exception {
		//生成角色选择
		Map<String, String> map = new HashMap<String, String>();
		map.put("role_type", String.valueOf(Constants.ROLE_TYPE_COMMON));
		map.put("subsys_code", "");
		roleList = userService.getSecRoleBySubsys(map);
		return SUCCESS;
	}
}
