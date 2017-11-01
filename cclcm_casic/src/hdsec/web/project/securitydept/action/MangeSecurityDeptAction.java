package hdsec.web.project.securitydept.action;

import hdsec.web.project.securitydept.model.SecurityDept;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MangeSecurityDeptAction extends DeptBaseAction {

	private String name = "";
	private String post = "";
	private String telephone = "";
	private String work = "";
	private String mobile = "";
	private String address = "";
	private String type = "";
	private String email= "";
	private String reark = "";
	private Integer rank = null;
	private List<SecurityDept> securityDeptList = null;
	private List<SecurityDept> securityCounicl = null ;//保密委员会
	private List<SecurityDept> securityTeam = null ;//保密处
	private List<SecurityDept> securityUser = null; //专职保密人员
	private List<SecurityDept> securityUserbydept = null; //部门兼（专）职保密人员
	@Override
	public String executeFunction() throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("mobile", mobile);
		map.put("post", post);
		if (type.equals("DEPT"))
		{
			securityDeptList = securityDeptService.getAllSecurityUsers(map);
		}else if(type.equals("SECERT")){
			securityDeptList = securityDeptService.getFixSecertUsers(map);
		}else{
			map.put("type", type);
			securityDeptList = securityDeptService.getSecurityUsers(map);
		}
		return SUCCESS;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<SecurityDept> getSecurityCounicl() {
		return securityCounicl;
	}
	public List<SecurityDept> getSecurityTeam() {
		return securityTeam;
	}
	public List<SecurityDept> getSecurityUser() {
		return securityUser;
	}
	public List<SecurityDept> getSecurityUserbydept() {
		return securityUserbydept;
	}
	public List<SecurityDept> getSecurityDeptList() {
		return securityDeptList;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
