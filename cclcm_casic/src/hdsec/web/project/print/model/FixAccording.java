package hdsec.web.project.print.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 定密依据管理类
 * 
 * @author renmingfei
 * 
 */
public class FixAccording extends BaseDomain {
	private String id = "";
	private String type = ""; // 类型
	private String content = ""; // 内容
	private String ext_code = ""; // 备用字段
	private String dept_id= "";

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType_name() {
		String name = "";
		switch (this.type) {
		case "1":
			name = "定密依据";
			break;
		case "2":
			name = "知悉范围";
			break;
		case "3":
			name = "保密期限";
			break;
		}
		return name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getExt_code() {
		return ext_code;
	}

	public void setExt_code(String ext_code) {
		this.ext_code = ext_code;
	}

	public FixAccording() {
		super();
	}

}
