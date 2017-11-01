package basic.xml;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserInfo {
	Depts depts = new Depts();
	Users users = new Users();

	public Depts getDepts() {
		return depts;
	}

	public void setDepts(Depts depts) {
		this.depts = depts;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
