package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysUsage;

import org.springframework.util.StringUtils;

/**
 * 添加用途
 * 
 * @author renmingfei
 * 
 */
public class AddUsageAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String usage_code = "";
	private String usage_name = "";
	private String usage_content = "";
	private String is_printbc = "";
	private String module_code = "";

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code.trim();
	}

	public void setUsage_name(String usage_name) {
		this.usage_name = usage_name.trim();
	}

	public void setUsage_content(String usage_content) {
		this.usage_content = usage_content;
	}

	public void setIs_printbc(String is_printbc) {
		this.is_printbc = is_printbc;
	}

	public void setModule_code(String module_code) {
		if (StringUtils.hasLength(module_code)) {
			this.module_code = "," + module_code.replaceAll(" ", "") + ",";
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (usage_code.isEmpty()) {
			return SUCCESS;
		} else {
			if (basicService.isUsageExist(usage_code, usage_name)) {
				throw new Exception("用户特征值或名称已经存在，不能重复添加");
			}
			SysUsage usage = new SysUsage(usage_code, usage_name, usage_content, is_printbc, module_code);
			basicService.addSysUsage(usage);
			insertAdminLog("添加用途：特征值[" + usage_code + "],用途名称[" + usage_name + "]");
			return "ok";
		}
	}

}
