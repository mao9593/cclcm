package hdsec.web.project.securityuser.model;
/**
 * 联系方式人
 * 
 * @author zhangzw
 * 
 */
public class CompanyContact {
	private int id;
	private String name;
	private String post;
	private String telephone;
	private String mobile;
	private String address;
	private String reark;
	private int rank;
	
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
	public CompanyContact() {
		super();
		
	}
	public CompanyContact(String name, String post, String telephone,
			String mobile, String address, String reark, int rank) {
		super();
		this.name = name;
		this.post = post;
		this.telephone = telephone;
		this.mobile = mobile;
		this.address = address;
		this.reark = reark;
		this.rank = rank;
	}
	
}
