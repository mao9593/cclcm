package hdsec.web.project.user.action;

import hdsec.web.project.user.model.RealUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看员工信息
 * @author renmingfei
 *
 */
public class ViewRealUserAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private List<RealUser> realUserList = null;
	private String base_username = "";
	private String job_passnum = "";
	private String com_telephone = "";
	private String binduser = "";
	
	public String getBase_username() {
		return base_username;
	}
	
	public void setBase_username(String base_username) {
		this.base_username = base_username;
	}
	
	public String getJob_passnum() {
		return job_passnum;
	}
	
	public void setJob_passnum(String job_passnum) {
		this.job_passnum = job_passnum;
	}
	
	public String getCom_telephone() {
		return com_telephone;
	}
	
	public void setCom_telephone(String com_telephone) {
		this.com_telephone = com_telephone;
	}
	
	public List<RealUser> getRealUserList() {
		return realUserList;
	}
	
	public String getBinduser() {
		return binduser;
	}
	
	public void setBinduser(String binduser) {
		this.binduser = binduser;
	}
	
	@Override
	public String executeFunction() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("base_username", base_username);
		map.put("job_passnum", job_passnum);
		map.put("com_telephone", com_telephone);
		map.put("binduser", binduser);
		realUserList = userService.getRealUser(map);
		return SUCCESS;
	}
}
