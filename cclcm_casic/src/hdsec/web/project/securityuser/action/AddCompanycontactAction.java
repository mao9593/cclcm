package hdsec.web.project.securityuser.action;

import hdsec.web.project.securityuser.model.CompanyContact;

public class AddCompanycontactAction extends SecurityUserBaseAction {

	private static final long serialVersionUID = 1L;
	private String name = "";
	private String post = "";
	private String telephone = "";
	private String mobile = "";
	private String address = "";
	private String reark = "";
	private Integer rank = 0;

	// private String defaults = "";

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

	@Override
	public String executeFunction() throws Exception {
		if (name.isEmpty() || name.equals("null")) {
			return "ok";
		}
		CompanyContact contact = new CompanyContact(name, post, telephone, mobile, address, reark, rank);
		securityUserService.addCompanyContact(contact);

		return SUCCESS;
	}

}
