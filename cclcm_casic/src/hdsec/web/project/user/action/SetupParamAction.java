package hdsec.web.project.user.action;

import hdsec.web.project.user.model.ConfigItem;

/**
 * 系统参数设置
 * @author renmingfei
 *
 */
public class SetupParamAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer alarm_percent;
	private Integer max_percent;
	private Integer space_used;
	//private String alarm_percent_start = "";
	private Integer store_period;
	//private String store_period_start = "";
	private String update = "N";
	private String done = "N";
	
	public Integer getAlarm_percent() {
		return alarm_percent;
	}
	
	public void setAlarm_percent(Integer alarm_percent) {
		this.alarm_percent = alarm_percent;
	}
	
	public Integer getMax_percent() {
		return max_percent;
	}
	
	public void setMax_percent(Integer max_percent) {
		this.max_percent = max_percent;
	}
	
	public Integer getSpace_used() {
		return space_used;
	}
	
	//	public String getAlarm_percent_start() {
	//		return alarm_percent_start;
	//	}
	//	
	//	public void setAlarm_percent_start(String alarm_percent_start) {
	//		this.alarm_percent_start = alarm_percent_start;
	//	}
	
	public Integer getStore_period() {
		return store_period;
	}
	
	public void setStore_period(Integer store_period) {
		this.store_period = store_period;
	}
	
	//	public String getStore_period_start() {
	//		return store_period_start;
	//	}
	//	
	//	public void setStore_period_start(String store_period_start) {
	//		this.store_period_start = store_period_start;
	//	}
	//	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public String getDone() {
		return done;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {//更新操作
			//			userService.updateConfigItem(new ConfigItem(ConfigItem.KEY_ALARM_PERCENT, String.valueOf(alarm_percent),
			//					alarm_percent_start.equals("1") ? 1 : 0));
			userService.updateConfigItem(new ConfigItem(ConfigItem.KEY_MAX_PERCENT, String.valueOf(max_percent), 1));
			userService.updateConfigItem(new ConfigItem(ConfigItem.KEY_STORE_PERIOD, String.valueOf(store_period), 1));
			userService
					.updateConfigItem(new ConfigItem(ConfigItem.KEY_ALARM_PERCENT, String.valueOf(alarm_percent), 1));
			insertAdminLog("系统参数设置");
			done = "Y";
		}
		ConfigItem item_space_used = userService.getConfigItemValue(ConfigItem.KEY_SPACE_USED);
		space_used = Integer.parseInt(item_space_used.getItem_value());
		ConfigItem item_alarm_percent = userService.getConfigItemValue(ConfigItem.KEY_ALARM_PERCENT);
		alarm_percent = Integer.parseInt(item_alarm_percent.getItem_value());
		ConfigItem item_max_percent = userService.getConfigItemValue(ConfigItem.KEY_MAX_PERCENT);
		max_percent = Integer.parseInt(item_max_percent.getItem_value());
		//alarm_percent_start = item_alarm_percent.getStartuse() == 1 ? "checked" : "";
		ConfigItem item_store_period = userService.getConfigItemValue(ConfigItem.KEY_STORE_PERIOD);
		store_period = Integer.parseInt(item_store_period.getItem_value());
		//store_period_start = item_store_period.getStartuse() == 1 ? "checked" : "";
		return SUCCESS;
	}
}
