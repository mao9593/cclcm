package hdsec.web.project.securitydept.action;

import hdsec.web.project.securitydept.model.SecurityDept;
import hdsec.web.project.securityuser.model.CompanyContact;

public class AddSecurityUserAction extends DeptBaseAction {

	private String name = "";
	private String post = "";
	private String telephone = "";
	private String work = "";
	private String mobile = "";
	private String address = "";
	private String type = "";
	private String email= "";
	private String reark = "";
	private Integer rank = 0;
	private String defaults = "";
	
	
	public void setWork(String work) {
		this.work = work;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPost(String post) {
		this.post = post;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setReark(String reark) {
		this.reark = reark;
	}
	
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public void setDefualts(String defualts) {
		this.defaults = defualts;
	}
	@Override
	public String executeFunction() throws Exception {
		if(name.isEmpty()||name.equals("null"))
		{
			return "ok";
		}
		SecurityDept contact = new SecurityDept(  name,  post,  work,  email, type,  telephone,  mobile, address,reark,  rank );
		securityDeptService.addSecurityUser(contact);
		return SUCCESS;
	}

}
