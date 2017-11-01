package hdsec.web.project.basic.action;

/**
 * 启用已停用的用途
 * 
 * @author lixiang
 * 
 */
public class RecoverUsageAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String usage_code = "";
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (usage_code.isEmpty()) {
			throw new Exception("参数错误，没有用途特征值");
		} else {
			basicService.recoverUsageByCode(usage_code);
			insertAdminLog("启用用途：特征值[" + usage_code + "]");
		}
		return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
	}
}
