package hdsec.web.project.securitydept.model;
/**
 * 联系方式人
 * 
 * @author zhangzw
 * 
 */
public class SecurityDept {

	private Integer id = null;
	private String name = null;
	private String post = null;
	private String work = null;
	private String email = null;
	private String type = null;
	private String telephone = null;
	private String mobile = null;
	private String address = null;
	private String reark = null;
	private Integer rank = null;
	
	public Integer getId() {
		return id;
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
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public SecurityDept() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * String name, String post, String work, String email,
	 *		String type, String telephone, String mobile, String address,
	 *		String reark, Integer rank
	 * 
	 * @author zhangzw
	 * 
	 */
	public SecurityDept(String name, String post, String work, String email,
			String type, String telephone, String mobile, String address,
			String reark, Integer rank) {
		super();
		this.name = name;
		this.post = post;
		this.work = work;
		this.email = email;
		this.type = type;
		this.telephone = telephone;
		this.mobile = mobile;
		this.address = address;
		this.reark = reark;
		this.rank = rank;
	}
}
