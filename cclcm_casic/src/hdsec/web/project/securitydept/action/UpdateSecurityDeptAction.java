package hdsec.web.project.securitydept.action;

import java.util.HashMap;
import java.util.Map;



import hdsec.web.project.securitydept.model.SecurityDept;
import hdsec.web.project.securityuser.model.CompanyContact;

public class UpdateSecurityDeptAction extends DeptBaseAction {

	private Integer id = -1;
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
	private SecurityDept contact = null;
	private String change = "";
	
	public String getWork() {
		return work;
	}

	public String getType() {
		return type;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setContact(SecurityDept contact) {
		this.contact = contact;
	}

	public Integer getId() {
		return id;
	}

	public SecurityDept getContact() {
		return contact;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReark() {
		return reark;
	}

	public void setReark(String reark) {
		this.reark = reark;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	@Override
	public String executeFunction() throws Exception {
		if(change.isEmpty())
		{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			contact = securityDeptService.getSecurityUsers(map).get(0);
			return SUCCESS;
		}
		else
		{
		    contact = new SecurityDept(  name,  post,  work,  email, type,  telephone,  mobile, address,reark,  rank );
		    contact.setId(id);
		    try{
		      securityDeptService.updatesecurityUser(contact);
		    }catch(Exception  e){
		    	return "exception";
		    }
			return "OK";
		}
		
	}

}
