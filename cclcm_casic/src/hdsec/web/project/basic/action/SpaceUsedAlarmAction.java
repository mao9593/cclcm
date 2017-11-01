package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

/**
 * 空间使用告警设置
 * 
 * @author lixiang
 * 
 */
public class SpaceUsedAlarmAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String space_used;// 当前磁盘空间使用百分比
	private String alarm_percent;// 当前磁盘空间告警百分比
	private String alarm_percent_start = "";
	private String update = "N";
	private String done = "N";
	
	public String getSpace_used() {
		return space_used;
	}
	
	public void setSpace_used(String space_used) {
		this.space_used = space_used;
	}
	
	public String getAlarm_percent() {
		return alarm_percent;
	}
	
	public void setAlarm_percent(String alarm_percent) {
		this.alarm_percent = alarm_percent;
	}
	
	public String getAlarm_percent_start() {
		return alarm_percent_start;
	}
	
	public void setAlarm_percent_start(String alarm_percent_start) {
		this.alarm_percent_start = alarm_percent_start;
	}
	
	public String getUpdate() {
		return update;
	}
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public String getDone() {
		return done;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {// 更新操作
			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_ALARM_PERCENT,
					SysConfigItem.NAME_ALARM_PERCENT, String.valueOf(alarm_percent), SysConfigItem.TYPE_SPACE, 1));
			insertAdminLog("设置空间使用告警参数");
			done = "Y";
		}
		SysConfigItem item_space_used = basicService.getSysConfigItemValue(SysConfigItem.KEY_SPACE_USED);
		space_used = item_space_used.getItem_value();
		
		SysConfigItem item_alarm_percent = basicService.getSysConfigItemValue(SysConfigItem.KEY_ALARM_PERCENT);
		alarm_percent = item_alarm_percent.getItem_value();
		
		return SUCCESS;
	}
}
