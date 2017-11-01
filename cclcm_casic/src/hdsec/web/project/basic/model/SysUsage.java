package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

public class SysUsage extends BaseDomain {
	private String usage_code = "";
	private String usage_name = "";
	private String usage_content = "";
	private String is_printbc = "";
	private String module_code = "";
	private String module_name = "";
	private String type = "";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getUsage_name() {
		return usage_name;
	}

	public void setUsage_name(String usage_name) {
		this.usage_name = usage_name;
	}

	public String getUsage_content() {
		return usage_content;
	}

	public void setUsage_content(String usage_content) {
		this.usage_content = usage_content;
	}

	public String getIs_printbc() {
		return is_printbc;
	}

	public void setIs_printbc(String is_printbc) {
		this.is_printbc = is_printbc;
	}

	public String getModule_code() {
		return module_code;
	}

	public void setModule_code(String module_code) {
		this.module_code = module_code;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public SysUsage() {
		super();
	}

	// 用途
	public SysUsage(String usage_code, String usage_name, String usage_content, String is_printbc, String module_code) {
		super();
		this.usage_code = usage_code;
		this.usage_name = usage_name;
		this.usage_content = usage_content;
		this.is_printbc = is_printbc;
		this.module_code = module_code;
	}

	// 制作类型
	public SysUsage(String usage_code, String usage_name, String usage_content, String type) {
		super();
		this.usage_code = usage_code;
		this.usage_name = usage_name;
		this.type = type;
		this.usage_content = usage_content;
	}
}
