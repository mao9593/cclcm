package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;

/**
 * 配置在线用户数门限值
 * 
 * @author renmingfei
 * 
 */
public class ConfigOuLimitAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private int normalValue = Constants.normalValue;
	private int warningValue = Constants.warningValue;
	private int criticalValue = Constants.criticalValue;
	private String update = "N";
	
	public int getNormalValue() {
		return normalValue;
	}
	
	public void setNormalValue(int normalValue) {
		this.normalValue = normalValue;
	}
	
	public int getWarningValue() {
		return warningValue;
	}
	
	public void setWarningValue(int warningValue) {
		this.warningValue = warningValue;
	}
	
	public int getCriticalValue() {
		return criticalValue;
	}
	
	public void setCriticalValue(int criticalValue) {
		this.criticalValue = criticalValue;
	}
	
	public String getUpdate() {
		return update;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {// 处理修改
			if (normalValue > 0 && warningValue > 0 && criticalValue > 0) {
				Constants.normalValue = normalValue;
				Constants.warningValue = warningValue;
				Constants.criticalValue = criticalValue;
				insertAdminLog("设置在线人数门限值：正常值" + normalValue + ",警告值" + warningValue + ",危险值" + criticalValue);
			} else {
				
			}
		}
		return SUCCESS;
	}
	
}
