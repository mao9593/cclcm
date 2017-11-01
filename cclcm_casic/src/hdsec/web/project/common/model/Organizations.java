package hdsec.web.project.common.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Organizations {
	List<Organization> Organization = new ArrayList<Organization>();

	public List<Organization> getOrganization() {
		return Organization;
	}

	public void setOrganization(List<Organization> organization) {
		Organization = organization;
	}

}
