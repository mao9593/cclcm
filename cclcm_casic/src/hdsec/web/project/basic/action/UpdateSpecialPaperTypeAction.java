package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysUsage;

public class UpdateSpecialPaperTypeAction extends BasicBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usage_code = "";
	private String usage_name = "";
	private String usage_content = "";
	private SysUsage usage = null;
	private String update = "N";

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

	public SysUsage getUsage() {
		return usage;
	}

	public void setUsage(SysUsage usage) {
		this.usage = usage;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			usage = basicService.getUsageByCode(usage_code);
			return SUCCESS;
		} else {
			usage = new SysUsage(usage_code, usage_name, usage_content, "N");
			basicService.updateUsage(usage);
			insertAdminLog("修改类型：特征值[" + usage_code + "],用途名称[" + usage_name + "],用途说明[" + usage_content + "].");
			return "ok";
		}
	}

}