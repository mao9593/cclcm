package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysUsage;

public class AddSpecialPaperTypeAction extends BasicBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String usage_code = "";
	private String usage_name = "";
	private String usage_content = "";

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String executeFunction() throws Exception {
		if (usage_code.isEmpty()) {
			return SUCCESS;
		} else {
			if (basicService.isUsageExist(usage_code, usage_name)) {
				throw new Exception("用户特征值或名称已经存在，不能重复添加");
			}
			SysUsage usage = new SysUsage(usage_code, usage_name, usage_content, "N");
			basicService.addSpecialPaperType(usage);
			insertAdminLog("添加用途：特征值[" + usage_code + "],用途名称[" + usage_name + "]");
			return "ok";
		}
	}

}