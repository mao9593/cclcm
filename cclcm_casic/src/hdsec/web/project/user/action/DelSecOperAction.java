package hdsec.web.project.user.action;

import java.util.HashMap;
import java.util.Map;

/**
 * 删除操作节点
 * @author renmingfei
 *
 */
public class DelSecOperAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String oper_code = "";
	private String subsys_code = "";
	private String deltype = "";
	
	public String getOper_code() {
		return oper_code;
	}
	
	public void setOper_code(String oper_code) {
		this.oper_code = oper_code;
	}
	
	public String getSubsys_code() {
		return subsys_code;
	}
	
	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}
	
	public String getDeltype() {
		return deltype;
	}
	
	public void setDeltype(String deltype) {
		this.deltype = deltype;
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("oper_code", oper_code);
		map.put("subsys_code", subsys_code);
		if (deltype.equalsIgnoreCase("class")) {//级联删除
			userService.delSecOperByCodeWithSub(oper_code, subsys_code);
		} else {
			userService.delSecOperByCode(map);
		}
		return SUCCESS;
	}
	
}
