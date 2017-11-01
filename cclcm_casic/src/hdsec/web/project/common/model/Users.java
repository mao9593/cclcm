package hdsec.web.project.common.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Users {
	List<UserInfo> user = new ArrayList<UserInfo>();

	public List<UserInfo> getUser() {
		return user;
	}

	public void setUser(List<UserInfo> user) {
		this.user = user;
	}

}
