package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

/**
 * 中物7所定制-部门载体归属需要个人确认取消管理员确认转换
 * 
 * @author guojiao
 */
public class ConfigDeptToPersonModeAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String update = "";
	private String type = "";
	private Integer onoff = null;

	public Integer getOnoff() {
		return onoff;
	}

	public void setOnoff(Integer onoff) {
		this.onoff = onoff;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {
			if (type.equalsIgnoreCase("open")) {
				basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_DEPTTOPERSON_MODE,
						SysConfigItem.NAME_DEPTTOPERSON_MODE, null, SysConfigItem.TYPE_DEPTTOPERSON_MODE, 1));
				insertAdminLog("部门载体归属转换个人确认关闭");
			} else {
				basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_DEPTTOPERSON_MODE,
						SysConfigItem.NAME_DEPTTOPERSON_MODE, null, SysConfigItem.TYPE_DEPTTOPERSON_MODE, 0));
				insertAdminLog("部门载体归属转换个人确认开启");
			}
		} else {
			if (basicService.getSysConfigItemValue(SysConfigItem.KEY_DEPTTOPERSON_MODE) != null) {
				onoff = basicService.getSysConfigItemValue(SysConfigItem.KEY_DEPTTOPERSON_MODE).getStartuse();
			}
		}
		return SUCCESS;
	}
}
