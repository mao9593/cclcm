package basic.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Depts {
	List<Organization> Organization = new ArrayList<Organization>();

	public List<Organization> getOrganization() {
		return Organization;
	}

	public void setOrganization(List<Organization> organization) {
		Organization = organization;
	}

}
