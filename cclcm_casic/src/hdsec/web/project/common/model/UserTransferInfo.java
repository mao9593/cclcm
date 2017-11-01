package hdsec.web.project.common.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserTransferInfo {
	Organizations depts = new Organizations();
	Users users = new Users();

	public Organizations getDepts() {
		return depts;
	}

	public void setDepts(Organizations depts) {
		this.depts = depts;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
