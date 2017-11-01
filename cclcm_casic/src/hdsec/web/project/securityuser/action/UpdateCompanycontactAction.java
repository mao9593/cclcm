package hdsec.web.project.securityuser.action;

import hdsec.web.project.securityuser.model.CompanyContact;

import java.util.HashMap;
import java.util.Map;

public class UpdateCompanycontactAction extends SecurityUserBaseAction {

	private static final long serialVersionUID = 1L;
	private Integer id = -1;
	private String name = "";
	private String post = "";
	private String telephone = "";
	private String mobile = "";
	private String address = "";
	private String reark = "";
	private Integer rank = 0;
	private CompanyContact contact = null;
	private String change = "";

	public void setContact(CompanyContact contact) {
		this.contact = contact;
	}

	public Integer getId() {
		return id;
	}

	public CompanyContact getContact() {
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
		if (change.isEmpty()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			contact = securityUserService.getCompanycontact(map).get(0);
			return SUCCESS;
		} else {
			contact = new CompanyContact(name, post, telephone, mobile, address, reark, rank);
			contact.setId(id);
			try {
				securityUserService.updateCompanyContact(contact);
			} catch (Exception e) {
				return "exception";
			}
			return "OK";
		}

	}

}
