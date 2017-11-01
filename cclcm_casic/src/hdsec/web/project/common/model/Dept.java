package hdsec.web.project.common.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Dept {
	private String dept_name;
	private String dept_id;
	private String desc;

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
