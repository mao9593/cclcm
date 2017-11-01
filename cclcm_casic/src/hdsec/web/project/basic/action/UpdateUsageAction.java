package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysUsage;

import org.springframework.util.StringUtils;

/**
 * 修改用途
 * 
 * @author renmingfei
 * 
 */
public class UpdateUsageAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String usage_code = "";
	private String usage_name = "";
	private String usage_content = "";
	private String is_printbc = "";
	private String module_code = "";
	private SysUsage usage = null;
	private String update = "N";

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setUsage_name(String usage_name) {
		this.usage_name = usage_name;
	}

	public void setUsage_content(String usage_content) {
		this.usage_content = usage_content;
	}

	public SysUsage getUsage() {
		return usage;
	}

	public void setIs_printbc(String is_printbc) {
		this.is_printbc = is_printbc;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public void setModule_code(String module_code) {
		if (StringUtils.hasLength(module_code)) {
			this.module_code = "," + module_code.replaceAll(" ", "") + ",";
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			usage = basicService.getUsageByCode(usage_code);
			return SUCCESS;
		} else {
			usage = new SysUsage(usage_code, usage_name, usage_content, is_printbc, module_code);
			basicService.updateUsage(usage);
			insertAdminLog("修改用途：特征值[" + usage_code + "],用途名称[" + usage_name + "],用途说明[" + usage_content + "].");
			return "ok";
		}
	}

}
