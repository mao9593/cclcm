package hdsec.web.project.securityuser.action;

import hdsec.web.project.securityuser.model.CompanyContact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询联系方式
 * 
 * @author zhangzw
 * 
 */
public class ContanctAction extends SecurityUserBaseAction {

	private static final long serialVersionUID = 1L;
	private int id = 0;
	private String name = null;
	private String post = null;
	private String telephone = null;
	private String mobile = null;
	private String address = null;
	private String reark = null;
	private int rank = 0;
	private List<CompanyContact> listContact;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public List<CompanyContact> getListContact() {
		return listContact;
	}

	@Override
	public String executeFunction() throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("post", post);
		map.put("telephone", telephone);
		map.put("mobile", mobile);
		map.put("reark", reark);
		map.put("address", address);
		listContact = securityUserService.getCompanycontact(map);
		return SUCCESS;
	}

}
