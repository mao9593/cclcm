package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

/**
 * 增加载体短期留用时间、回收提醒周期配置
 * 
 * @author gaoximin 2014-9-10
 */
public class ConfigRemindMsgAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String import_short_days;// 载体短期留用时间
	private String remind_msg_days;// 载体回收消息提醒周期
	private String import_short_days_start = "";
	private String remind_msg_days_start = "";
	private Integer startuse_import = 0;
	private Integer startuse_remind = 0;
	private String update = "N";
	private String done = "N";

	public String getImport_short_days() {
		return import_short_days;
	}

	public void setImport_short_days(String import_short_days) {
		this.import_short_days = import_short_days;
	}

	public String getRemind_msg_days() {
		return remind_msg_days;
	}

	public void setRemind_msg_days(String remind_msg_days) {
		this.remind_msg_days = remind_msg_days;
	}

	public String getImport_short_days_start() {
		return import_short_days_start;
	}

	public void setImport_short_days_start(String import_short_days_start) {
		this.import_short_days_start = import_short_days_start;
	}

	public String getRemind_msg_days_start() {
		return remind_msg_days_start;
	}

	public void setRemind_msg_days_start(String remind_msg_days_start) {
		this.remind_msg_days_start = remind_msg_days_start;
	}

	public Integer getStartuse_import() {
		return startuse_import;
	}

	public void setStartuse_import(Integer startuse_import) {
		this.startuse_import = startuse_import;
	}

	public Integer getStartuse_remind() {
		return startuse_remind;
	}

	public void setStartuse_remind(Integer startuse_remind) {
		this.startuse_remind = startuse_remind;
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

	public void setDone(String done) {
		this.done = done;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {// 更新操作
			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_IMPORT_SHORT_DAYS,
					SysConfigItem.NAME_IMPORT_SHORT_DAYS, String.valueOf(import_short_days), SysConfigItem.TYPE_REMIND,
					1));
			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_REMIND_MSG_DAYS,
					SysConfigItem.NAME_REMIND_MSG_DAYS, String.valueOf(remind_msg_days), SysConfigItem.TYPE_REMIND,
					remind_msg_days_start.equals("1") ? 1 : 0));
			insertAdminLog("设置消息提醒时间");
			done = "Y";
		}
		SysConfigItem item_import_short_days = basicService.getSysConfigItemValue(SysConfigItem.KEY_IMPORT_SHORT_DAYS);
		import_short_days = item_import_short_days.getItem_value();
		import_short_days_start = item_import_short_days.getStartuse() == 1 ? "checked" : "";

		SysConfigItem item_remind_msg_days = basicService.getSysConfigItemValue(SysConfigItem.KEY_REMIND_MSG_DAYS);
		remind_msg_days = item_remind_msg_days.getItem_value();
		remind_msg_days_start = item_remind_msg_days.getStartuse() == 1 ? "checked" : "";

		return SUCCESS;
	}
}
